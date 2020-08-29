package com.subscription.service.application.model;

import java.util.List;

import com.subscription.service.application.util.SubscriptionType;

/**
 * @author Victor G. Andales III
 *
 */
public class Invoice {

	private double amount;
	private SubscriptionType subscriptionType;
	private List<String> invoiceDates;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public List<String> getInvoiceDates() {
		return invoiceDates;
	}

	public void setInvoiceDates(List<String> invoiceDates) {
		this.invoiceDates = invoiceDates;
	}

}
