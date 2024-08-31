package com.autobill.webhook.services

import com.autobill.webhook.IntegrationApplication
import com.autobill.webhook.IntegrationProperty
import com.autobill.webhook.src.handler.AutoBillHandler
import com.autobill.webhook.src.handler.WebhookHandlerMapping
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class IntegrationService {

    @Transactional
    def saveIntegration(IntegrationProperty integrationProperty) {
        integrationProperty = integrationProperty.save()
        return !integrationProperty.hasErrors()
    }

    @Transactional
    def deleteIntegration(Long id) {
        IntegrationProperty integrationProperty = getIntegrationPropertyById(id)
        if (integrationProperty == null) {
            throw new Exception("No such integrationProperty found")
        }
        integrationProperty.delete()
        return true
    }

    @Transactional
    def searchIntegrations(String application, String json) {
        IntegrationApplication integrationApplication = IntegrationApplication.findByName(application)
        if (integrationApplication == null)
        {
            throw new Exception("No such application found")
        }
        AutoBillHandler autoBillHandler = WebhookHandlerMapping.getHandlerByName(application)
        List<IntegrationProperty> integrationPropertyList = IntegrationProperty.findAllByApplication(
            integrationApplication
        )
        def integratedInstance8DigitList = []
        def jsonObject = JSON.parse(json)
        for(IntegrationProperty integrationProperty : integrationPropertyList){
            if(autoBillHandler.matchProperty(integrationProperty, jsonObject)){
                integratedInstance8DigitList.add(integrationProperty.code)
            }
        }
        return integratedInstance8DigitList
    }

    IntegrationProperty getIntegrationPropertyById(long id){
        return IntegrationProperty.get(id)
    }

    @Transactional
    IntegrationProperty getIntegrationByExternalInstanceId(externalInstanceId){
        return IntegrationProperty.findByExternalInstanceId(externalInstanceId)
    }

    List<IntegrationProperty> getAllIntegrationProperties(){
        return IntegrationProperty.findAll()
    }
}
