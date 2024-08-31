package com.autobill.webhook.services

import com.autobill.webhook.WebhookProperty
import com.autobill.webhook.src.resolver.RequestDataResolver
import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional
class OrderRequestDataResolverService implements RequestDataResolver {

    private String prepareOrderCreateData(String data) {
        def accountData = JSON.parse(data)
        return accountData.order as JSON
    }

    @Override
    String prepareRequestData(WebhookProperty webhookProperty) {
        return prepareOrderCreateData(webhookProperty.data)
    }
}
