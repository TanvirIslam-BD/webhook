package com.autobill.webhook.services

import com.autobill.webhook.WebhookProperty
import com.autobill.webhook.src.resolver.RequestDataResolver
import grails.converters.JSON
import org.grails.web.json.JSONElement

class ShipmentRequestDataResolverService implements RequestDataResolver {

    private String prepareShipmentCreateData(String data) {
        JSONElement paymentData = JSON.parse(data)
        return paymentData.resource.fulfillment as JSON
    }

    @Override
    String prepareRequestData(WebhookProperty webhookProperty) {
        return prepareShipmentCreateData(webhookProperty.data)
    }
}
