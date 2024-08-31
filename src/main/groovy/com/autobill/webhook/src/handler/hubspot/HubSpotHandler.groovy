package com.autobill.webhook.src.handler.hubspot

import com.autobill.webhook.IntegrationApplication
import com.autobill.webhook.IntegrationProperty
import com.autobill.webhook.src.Constant.Constants
import com.autobill.webhook.src.Instance
import com.autobill.webhook.src.cachedrequest.CachedBodyHttpServletRequest
import com.autobill.webhook.src.handler.AutoBillHandler
import grails.converters.JSON
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONElement
import org.grails.web.json.JSONObject

import javax.servlet.http.Cookie
import javax.servlet.http.HttpSession

class HubSpotHandler extends AutoBillHandler{
    @Override
    List<Instance> determineInstance(CachedBodyHttpServletRequest httpServletRequest, HttpSession httpSession, Cookie[] cookies) {
        List<Instance> instanceList = []
        List<IntegrationProperty> hubSpotParameters = IntegrationProperty.findAllByApplication(IntegrationApplication.findByName(Constants.APPLICATIONS.HUBSPOT))
        for(IntegrationProperty integrationProperty : hubSpotParameters ){
            JSONArray payLoad = httpServletRequest.getJSON() as JSONArray
            Map jsonGroupByPortal = payLoad.groupBy{ ((JSONObject) it).has(Constants.REQUEST_IDENTIFIER.HUBSPOT) ? ((JSONObject) it)[Constants.REQUEST_IDENTIFIER.HUBSPOT] : false }
            jsonGroupByPortal.each { jsonObject ->
                if(JSON.parse(integrationProperty.data)[Constants.REQUEST_IDENTIFIER.HUBSPOT].toString() == jsonObject.value[0][Constants.REQUEST_IDENTIFIER.HUBSPOT].toString() ){
                    instanceList.add(new Instance(integrationProperty.port, integrationProperty.code, integrationProperty.path, (jsonObject.value as JSON).toString(), httpServletRequest.getQueryString()))
                }
            }
        }
        return instanceList
    }

    @Override
    boolean matchProperty(IntegrationProperty integrationProperty, JSONElement jsonElement) {
        return JSON.parse(integrationProperty.data)[Constants.REQUEST_IDENTIFIER.HUBSPOT].toString() == jsonElement[Constants.REQUEST_IDENTIFIER.HUBSPOT].toString()
    }
}
