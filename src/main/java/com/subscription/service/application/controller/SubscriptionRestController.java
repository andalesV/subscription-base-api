package com.subscription.service.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subscription.service.application.model.SubscriptionError;
import com.subscription.service.application.model.Invoice;
import com.subscription.service.application.model.SubscriptionInfo;
import com.subscription.service.application.service.SubscriptionService;

/**
 * @author Victor G. Andales III
 *
 */
@RestController
public class SubscriptionRestController {

	private static final String ERROR = "Transaction Error";
	private static final String GENERAL_ERROR_MESSAGE = "Something went wrong on your transaction.";

	@Autowired
	private SubscriptionService subscriptionService;

	@PostMapping("/create/subscription")
	public Object createSubscription(@RequestBody SubscriptionInfo subscriptionInfo) {

		Invoice invoice;

		try {
			invoice = subscriptionService.createSubscription(subscriptionInfo);
			if (invoice != null) {
				return invoice;
			}
		} catch (Exception e) {
			return getErrorMessage(e.getLocalizedMessage());

		}
		return getErrorMessage();
	}

	private SubscriptionError getErrorMessage() {
		SubscriptionError error = new SubscriptionError();
		error.setError(ERROR);
		error.setErrorMessage(GENERAL_ERROR_MESSAGE);
		return error;
	}

	private SubscriptionError getErrorMessage(final String errorMessage) {
		SubscriptionError error = new SubscriptionError();
		error.setError(ERROR);
		error.setErrorMessage(errorMessage);
		return error;
	}
}