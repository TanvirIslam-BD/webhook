package com.autobill.webhook.services

import com.autobill.webhook.WebhookProperty
import com.autobill.webhook.src.resolver.RequestDataResolver
import grails.converters.JSON

class AccountRequestDataResolverService implements RequestDataResolver {

    private String prepareAccountCreateData(String data) {
        def accountData = JSON.parse(data)
        return accountData.resource.account as JSON
    }

    @Override
    String prepareRequestData(WebhookProperty webhookProperty) {
        return prepareAccountCreateData(webhookProperty.data)
    }
}
