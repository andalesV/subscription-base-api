package com.subscription.service.application.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.subscription.service.application.model.Invoice;
import com.subscription.service.application.model.SubscriptionInfo;
import com.subscription.service.application.util.Day;
import com.subscription.service.application.util.DayUtil;
import com.subscription.service.application.util.SubscriptionType;

/**
 * This service implementor class is for processing and validating subscription
 * related transactions.
 * 
 * @author Victor G. Andales III
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Value("${invoice.date.format}")
	private String dateFormat;

	private Day day;
	private int dayInt;
	private SubscriptionType subscriptionType;

	@Override
	public Invoice createSubscription(SubscriptionInfo subscriptionInfo) {
		subscriptionType = subscriptionInfo.getSubscriptionType();
		return checkSubscription(subscriptionInfo);
	}

	private Invoice checkSubscription(final SubscriptionInfo subscriptionInfo) {
		evaluateScope(subscriptionInfo.getScope());

		String startDate = subscriptionInfo.getDuration().getStartDate();
		String endDate = subscriptionInfo.getDuration().getEndDate();

		if (isSubscriptionDatesValid(startDate, endDate)) {
			Invoice invoice = new Invoice();
			invoice.setAmount(subscriptionInfo.getAmount());
			invoice.setSubscriptionType(subscriptionType);
			List<LocalDate> invoiceDates = processInvoiceDates(startDate, endDate);
			invoice.setInvoiceDates(formatInvoiceDates(invoiceDates));
			return invoice;
		}
		return null;

	}

	/**
	 * Format invoice date to dd/MM/yyyy.
	 * 
	 * @param invoiceDates list of invoice dates
	 * @return invoice dates list
	 */
	private List<String> formatInvoiceDates(List<LocalDate> invoiceDates) {
		List<String> formattedDates = new ArrayList<String>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

		for (LocalDate date : invoiceDates) {
			formattedDates.add(formatter.format(date));
		}

		return formattedDates;
	}

	private void evaluateScope(final Object cope) {
		if (cope instanceof Integer) {
			this.dayInt = (int) cope;
		} else {
			this.day = Day.valueOf(cope.toString());
		}
	}

	private boolean isSubscriptionDatesValid(final String startDate, final String endDate) {
		LocalDate localStartDate = getStartDate(startDate);
		LocalDate localEndDate = getEndDate(endDate);

		long countDuration = ChronoUnit.MONTHS.between(localStartDate, localEndDate);
		return Long.sum(countDuration, 1) <= 3;
	}

	/**
	 * Process date invoice.
	 * 
	 * @param startDate date on which subscription start
	 * @param endDate   date on which subscription end
	 * @return
	 */
	private List<LocalDate> processInvoiceDates(final String startDate, final String endDate) {
		LocalDate localStartDate = getStartDate(startDate);
		LocalDate localEndDate = getEndDate(endDate);

		switch (subscriptionType) {
		case WEEKLY:
			return getDateListByWeek(localStartDate, localEndDate);
		case MONTHLY:
			return getDateListByMonth(localStartDate, localEndDate);
		default:
			return null;
		}

	}

	/**
	 * Get list of dates by week.
	 * 
	 * @param localStartDate date on which subscription start
	 * @param localEndDate   date on which subscription end
	 * @return
	 */
	private List<LocalDate> getDateListByWeek(final LocalDate localStartDate, final LocalDate localEndDate) {
		long numOfWeeksBetween = ChronoUnit.WEEKS.between(localStartDate, localEndDate);

		return IntStream.iterate(0, i -> i + 1).limit(Long.sum(numOfWeeksBetween, 1)).mapToObj(i -> localStartDate.plusWeeks(i))
				.collect(Collectors.toList());
	}

	/**
	 * Get list of dates by month.
	 * 
	 * @param localStartDate date on which subscription start
	 * @param localEndDate   date on which subscription end
	 * @return
	 */
	private List<LocalDate> getDateListByMonth(final LocalDate localStartDate, final LocalDate localEndDate) {
		long numOfWeeksBetween = ChronoUnit.MONTHS.between(localStartDate, localEndDate);

		return IntStream.iterate(0, i -> i + 1).limit(Long.sum(numOfWeeksBetween, 1)).mapToObj(i -> localStartDate.plusMonths(i))
				.collect(Collectors.toList());
	}

	/**
	 * Get value for start date.
	 * 
	 * @param startDate value from request body for start date
	 * @return start date base from subscription type.
	 */
	private LocalDate getStartDate(final String startDate) {
		String[] startDates = startDate.split("/");
		int startDateMonth = Integer.parseInt(startDates[0]);
		int startDateYear = Integer.parseInt(startDates[1]);
		LocalDate localStartDate = null;

		switch (subscriptionType) {
		case WEEKLY:
			localStartDate = LocalDate.of(startDateYear, startDateMonth, 1);
			LocalDate specificDay =  localStartDate.with(DayUtil.getDayOfWeek(day));
			if (specificDay.compareTo(localStartDate) < 0) {
				return specificDay.plusWeeks(1);
			} else {
				return specificDay;
			}
			
		case MONTHLY:
			return LocalDate.of(startDateYear, startDateMonth, dayInt).plusMonths(1);
		default:
			return null;
		}

	}

	/**
	 * Get end date.
	 * 
	 * @param endDate value from request body for end date
	 * @return end date
	 */
	private LocalDate getEndDate(final String endDate) {
		String[] endDates = endDate.split("/");
		int endDateMonth = Integer.parseInt(endDates[0]);
		int endDateYear = Integer.parseInt(endDates[1]);

		YearMonth yearMonthObject = YearMonth.of(endDateYear, endDateMonth);
		int lastDayOfMonth = yearMonthObject.lengthOfMonth();

		return LocalDate.of(endDateYear, endDateMonth, lastDayOfMonth);
	}

}