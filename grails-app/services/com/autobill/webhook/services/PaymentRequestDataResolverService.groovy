package com.autobill.webhook.services

import com.autobill.webhook.WebhookProperty
import com.autobill.webhook.src.resolver.RequestDataResolver
import grails.converters.JSON

class PaymentRequestDataResolverService implements RequestDataResolver{

    private String preparePaymentCreateData(String data) {
        def paymentData = JSON.parse(data)
        return paymentData.payment as JSON
    }

    @Override
    String prepareRequestData(WebhookProperty webhookProperty) {
        return preparePaymentCreateData(webhookProperty.data)
    }
}
