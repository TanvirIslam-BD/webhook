package com.autobill.webhook.src.init

import com.autobill.webhook.IntegrationProperty
import grails.util.Environment

class ApplicationInit {
    static void init() {
        setAnIntegration()
    }

    static setAnIntegration() {
        List<IntegrationProperty> integrationProperties = IntegrationProperty.findAll()
        if (Environment.isDevelopmentMode() && integrationProperties.size() == 0) {
            IntegrationProperty integrationProperty = new IntegrationProperty()
            integrationProperty.setExternalInstanceId("8358ae58")
            integrationProperty.setWcInstanceName("localhost")
            integrationProperty.setWcInstancePath("http://localhost:1301")
            integrationProperty.save()
        }
    }
}
