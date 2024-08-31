package com.autobill.webhook.services

import com.autobill.webhook.WebhookProperty
import com.autobill.webhook.src.resolver.RequestDataResolver
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONElement

@Transactional
class ProductRequestDataResolverService implements RequestDataResolver {

    private String prepareProductCreateData(WebhookProperty webhookProperty) {
        JSONElement productData = JSON.parse(webhookProperty.data)
        if(webhookProperty.actionName.equalsIgnoreCase("adjustInventory")){
            return productData.resource as JSON
        }
        return productData.item as JSON
    }

    @Override
    String prepareRequestData(WebhookProperty webhookProperty) {
        return prepareProductCreateData(webhookProperty)
    }
}
