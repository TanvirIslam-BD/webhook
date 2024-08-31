package com.autobill.webhook.services

import com.autobill.webhook.IntegrationProperty
import com.autobill.webhook.WebhookProperty
import com.autobill.webhook.src.cachedrequest.CachedBodyHttpServletRequest
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class WebhookPropertyService {

    IntegrationService integrationService
    @Autowired
    AccountRequestUrlResolverService accountRequestUrlResolver
    @Autowired
    ProductRequestUrlResolvelService productRequestUrlResolver
    @Autowired
    OrderRequestUrlResolverService orderRequestUrlResolverService
    @Autowired
    PaymentRequestUrlResolverService paymentRequestUrlResolverService
    @Autowired
    ShipmentRequestUrlResolverService shipmentRequestUrlResolverService

    @Autowired
    AccountRequestDataResolverService accountRequestDataResolver
    @Autowired
    ProductRequestDataResolverService productRequestDataResolver
    @Autowired
    OrderRequestDataResolverService orderRequestDataResolverService
    @Autowired
    PaymentRequestDataResolverService paymentRequestDataResolverService
    @Autowired
    ShipmentRequestDataResolverService shipmentRequestDataResolverService


    @Transactional
    def saveWebhookProperty(String webhookURL, CachedBodyHttpServletRequest cachedBodyHttpServletRequest) {
        def actionProperties = webhookURL.split("\\/")
        if (actionProperties.size() == 2) {
            String entityName = actionProperties[0]
            String actionName = actionProperties[1]
            String method = cachedBodyHttpServletRequest.getMethod()
            def requestBody = cachedBodyHttpServletRequest.getJSON()
            String requestInstanceId = requestBody.webhook.instance_id
            String data = requestBody.webhook
            WebhookProperty webhookProperty = new WebhookProperty()
            webhookProperty.setEntityName(entityName)
            webhookProperty.setActionName(actionName)
            webhookProperty.setMethod(method)
            webhookProperty.setRequestInstanceId(requestInstanceId)
            webhookProperty.setData(data)
            webhookProperty.save()
            if (!webhookProperty.hasErrors()) {
                return true
            } else {
                return false
            }
        }
        return false
    }

    List findAll(){
        return WebhookProperty.createCriteria().list {
            eq("isSuccess", false)
            eq("isCurrentlyRunning", false)
            lt("retryCount", 10)
        }
    }

    Boolean isRequestSuccess(Integer code) {
        if (code == HttpStatus.OK.value() || code == HttpStatus.CREATED.value() || code == HttpStatus.ACCEPTED.value() || code == HttpStatus.NO_CONTENT.value()) {
            return true
        }
        return false
    }

    @Transactional
    def saveRetryCount(WebhookProperty webhookProperty, Integer code, String response){
        webhookProperty.isCurrentlyRunning = false
        if (isRequestSuccess(code)) {
            webhookProperty.isSuccess = true
        } else {
            webhookProperty.retryCount = webhookProperty.retryCount + 1
        }
        webhookProperty.response = response
        return webhookProperty.save()
    }

    @Transactional
    def setAsCurrentlyRunning(WebhookProperty webhookProperty){
        webhookProperty.isCurrentlyRunning = true
        return webhookProperty.save()
    }


    String resolveFullUrl(String baseUrl, WebhookProperty webhookProperty) {
        if (webhookProperty.entityName == "account") {
            return baseUrl + accountRequestUrlResolver.resolveFullUrl(webhookProperty.actionName)
        } else if (webhookProperty.entityName == "product") {
            return baseUrl + productRequestUrlResolver.resolveFullUrl(webhookProperty.actionName)
        } else if (webhookProperty.entityName == "order") {
            return baseUrl + orderRequestUrlResolverService.resolveFullUrl(webhookProperty.actionName)
        } else if (webhookProperty.entityName == "payment") {
            return baseUrl + paymentRequestUrlResolverService.resolveFullUrl(webhookProperty.actionName)
        } else if (webhookProperty.entityName == "shipment") {
            return baseUrl + shipmentRequestUrlResolverService.resolveFullUrl(webhookProperty.actionName)
        }
    }

    void setFullUrl(WebhookProperty webhookProperty) {
        IntegrationProperty integrationProperty = integrationService.getIntegrationByExternalInstanceId(webhookProperty.requestInstanceId)
        if (integrationProperty) {
            String baseUrl = integrationProperty.getWcInstancePath()
            String fullUrl = resolveFullUrl(baseUrl, webhookProperty)
            webhookProperty.setFullUrl(fullUrl)
        }
    }

    void prepareData(WebhookProperty webhookProperty) {
        String data = ""
        if (webhookProperty.entityName == "account") {
            data = accountRequestDataResolver.prepareRequestData(webhookProperty)
        } else if (webhookProperty.entityName == "product") {
            data = productRequestDataResolver.prepareRequestData(webhookProperty)
        } else if (webhookProperty.entityName == "order") {
            data = orderRequestDataResolverService.prepareRequestData(webhookProperty)
        } else if (webhookProperty.entityName == "payment") {
            data = paymentRequestDataResolverService.prepareRequestData(webhookProperty)
        } else if (webhookProperty.entityName == "shipment") {
            data = shipmentRequestDataResolverService.prepareRequestData(webhookProperty)
        }
        webhookProperty.setPreparedData(data)
    }

}
