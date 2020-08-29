package com.subscription.service.application.util;

import java.time.DayOfWeek;

/**
 * @author Victor G. Andales III
 *
 */
public class DayUtil {

	public static DayOfWeek getDayOfWeek(final Day day) {
		switch (day) {
		case MONDAY:
			return DayOfWeek.MONDAY;
		case TUESDAY:
			return DayOfWeek.TUESDAY;
		case WEDNESDAY:
			return DayOfWeek.WEDNESDAY;
		case THURSDAY:
			return DayOfWeek.THURSDAY;
		case FRIDAY:
			return DayOfWeek.FRIDAY;
		case SATURDAY:
			return DayOfWeek.SATURDAY;
		case SUNDAY:
			return DayOfWeek.SUNDAY;
		}
		return null;
	}

}
