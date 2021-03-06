package com.subscription.service.application.model;

/**
 * @author Victor G. Andales III
 *
 */
public class SubscriptionError {
	private String error;
	private String errorMessage;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
