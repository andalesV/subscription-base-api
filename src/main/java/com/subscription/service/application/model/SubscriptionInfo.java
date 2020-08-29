package com.subscription.service.application.model;

import com.subscription.service.application.util.SubscriptionType;

/**
 * @author Victor G. Andales III
 *
 */
public class SubscriptionInfo {

	private double amount;
	private SubscriptionType subscriptionType;
	private Object scope;
	private Duration duration;

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setScope(Object scope) {
		this.scope = scope;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Object getScope() {
		return scope;
	}

}