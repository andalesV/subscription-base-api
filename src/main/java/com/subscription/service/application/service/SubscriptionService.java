package com.subscription.service.application.service;

import com.subscription.service.application.model.Invoice;
import com.subscription.service.application.model.SubscriptionInfo;

/**
 * @author Victor G. Andales III
 *
 */
public interface SubscriptionService {

	public Invoice createSubscription(SubscriptionInfo subscriptionInfo) throws Exception;

}
