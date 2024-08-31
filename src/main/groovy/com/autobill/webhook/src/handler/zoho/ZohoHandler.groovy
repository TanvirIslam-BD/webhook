package com.autobill.webhook.src.handler.zoho

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

class ZohoHandler extends AutoBillHandler{
    @Override
    List<Instance> determineInstance(CachedBodyHttpServletRequest httpServletRequest, HttpSession httpSession, Cookie[] cookies) {
        List<Instance> instanceList = []
        List<IntegrationProperty> integrationProperties = IntegrationProperty.findAllByApplication(IntegrationApplication.findByName(Constants.APPLICATIONS.ZOHO))
        for(IntegrationProperty integrationProperty : integrationProperties ){
            String identifier = httpServletRequest.getParameter(Constants.REQUEST_IDENTIFIER.ZOHO)

            if(JSON.parse(integrationProperty.data)[Constants.REQUEST_IDENTIFIER.ZOHO].toString() == identifier){
                instanceList.add(new Instance(integrationProperty.port, integrationProperty.code, integrationProperty.path, httpServletRequest.getJSON().toString(), httpServletRequest.getQueryString()))
            }
        }
        return instanceList
    }

    @Override
    boolean matchProperty(IntegrationProperty integrationProperty, JSONElement jsonElement) {
        return JSON.parse(integrationProperty.data)[Constants.REQUEST_IDENTIFIER.ZOHO].toString() == jsonElement[Constants.REQUEST_IDENTIFIER.ZOHO].toString()
    }
}
