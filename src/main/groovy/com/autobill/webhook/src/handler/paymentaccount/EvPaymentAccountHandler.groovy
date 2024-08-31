package com.autobill.webhook.src.handler.paymentaccount

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

class EvPaymentAccountHandler extends AutoBillHandler {
    @Override
    List<Instance> determineInstance(CachedBodyHttpServletRequest httpServletRequest, HttpSession httpSession, Cookie[] cookies) {
        List<Instance> instanceList = []
        List<IntegrationProperty> integrationProperties = IntegrationProperty.findAllByApplication(IntegrationApplication.findByName(Constants.APPLICATIONS.EV_PAYMENT_ACCOUNT))
        for (IntegrationProperty integrationProperty : integrationProperties) {
            String identifier = httpServletRequest.getParameter(Constants.REQUEST_IDENTIFIER.EV_PAYMENT_ACCOUNT)

            if (JSON.parse(integrationProperty.data)[Constants.REQUEST_IDENTIFIER.EV_PAYMENT_ACCOUNT].toString() == identifier) {
                instanceList.add(new Instance(integrationProperty.port, integrationProperty.code, integrationProperty.path, httpServletRequest.getJSON().toString(), httpServletRequest.getQueryString()))
            }
        }
        return instanceList
    }

    @Override
    boolean matchProperty(IntegrationProperty integrationProperty, JSONElement jsonElement) {
        return JSON.parse(integrationProperty.data)[Constants.REQUEST_IDENTIFIER.EV_PAYMENT_ACCOUNT].toString() == jsonElement[Constants.REQUEST_IDENTIFIER.EV_PAYMENT_ACCOUNT].toString()
    }
}
