package com.autobill.webhook.src.handler.docusign

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

class DocuSignHandler extends AutoBillHandler{

    @Override
    List<Instance> determineInstance(CachedBodyHttpServletRequest httpServletRequest, HttpSession httpSession, Cookie[] cookies) {
        List<Instance> instanceList = []
        List<IntegrationProperty> integrationProperties = IntegrationProperty.findAllByApplication(IntegrationApplication.findByName(Constants.APPLICATIONS.DOCU_SIGN))
        for(IntegrationProperty integrationProperty : integrationProperties ){
            instanceList.add(new Instance(integrationProperty.port, integrationProperty.code, integrationProperty.path, httpServletRequest.getJSON().toString(), httpServletRequest.getQueryString()))
        }
        return instanceList
    }

    @Override
    boolean matchProperty(IntegrationProperty integrationProperty, JSONElement jsonElement) {
        return false
    }
}
