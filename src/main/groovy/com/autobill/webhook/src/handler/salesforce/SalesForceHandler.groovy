package com.autobill.webhook.src.handler.salesforce

import com.autobill.webhook.IntegrationApplication
import com.autobill.webhook.IntegrationProperty
import com.autobill.webhook.src.Constant.Constants
import com.autobill.webhook.src.Instance
import com.autobill.webhook.src.cachedrequest.CachedBodyHttpServletRequest
import com.autobill.webhook.src.handler.AutoBillHandler
import grails.converters.JSON
import org.grails.web.json.JSONElement

import javax.servlet.http.Cookie
import javax.servlet.http.HttpSession

class SalesForceHandler extends AutoBillHandler{
    @Override
    List<Instance> determineInstance(CachedBodyHttpServletRequest httpServletRequest, HttpSession httpSession, Cookie[] cookies) {
        List<Instance> instanceList = []
        List<IntegrationProperty> integrationProperties = IntegrationProperty.findAllByApplication(IntegrationApplication.findByName(Constants.APPLICATIONS.SALESFORCE))
        for(IntegrationProperty integrationProperty : integrationProperties ){
            Object jsonObject = JSON.parse(parseWebHookResponse(httpServletRequest).toString())
            Map identifier = jsonObject
            if(JSON.parse(integrationProperty.data)[Constants.REQUEST_IDENTIFIER.SALESFORCE].toString() == identifier.OrganizationId){
                instanceList.add(new Instance(integrationProperty.port, integrationProperty.code, integrationProperty.path, parseWebHookResponse(httpServletRequest).toString(), httpServletRequest.getQueryString()))
            }
        }
        return instanceList
    }

    @Override
    boolean matchProperty(IntegrationProperty integrationProperty, JSONElement jsonElement) {
        return JSON.parse(integrationProperty.data)[Constants.REQUEST_IDENTIFIER.SALESFORCE].toString() == jsonElement[Constants.REQUEST_IDENTIFIER.SALESFORCE].toString()
    }

    static def parseWebHookResponse(CachedBodyHttpServletRequest httpServletRequest){

        def response = new String(httpServletRequest.inputStream.bytes)
        if (response == "") {
            return null
        } else {
            def responseXml = new XmlSlurper(false, false).parseText(response)
            def notifications = responseXml.'**'.find{ it.name() == 'notifications' }[0].children()
            Map responseDetails = [:]
            for (notification in notifications) {
                if(notification.name == "Notification") {
                    def notificationId = notification.children[0].children[0]
                    def objectId = notification.children[1].children[0].children[0]
                    def objectType = notification.children[1].attributes()["xsi:type"]
                    responseDetails["ObjectInfo"] =  [notificationId: notificationId, objectId : objectId, objectType : objectType]
                }
                else {
                    responseDetails[notification.name.toString()] = notification.children[0]
                }
            }
            return responseDetails as JSON
        }
    }
}
