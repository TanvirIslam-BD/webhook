package com.autobill.webhook.controllers

import com.autobill.webhook.IntegrationProperty
import com.autobill.webhook.services.IntegrationService
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_ADMIN")
class IntegrationController {

    IntegrationService integrationService

    def view() {
        List<IntegrationProperty> integrationProperties = integrationService.getAllIntegrationProperties()
        render(view: "/integration/listView", model: [integrationProperties: integrationProperties])
    }

    def edit() {
        IntegrationProperty integrationProperty = params.id ? integrationService.getIntegrationPropertyById(params.id.toLong()) : new IntegrationProperty()
        render(view: "/integration/edit", model: [integrationProperty: integrationProperty])
    }

    def update() {
        IntegrationProperty integrationProperty = params.id ? integrationService.getIntegrationPropertyById(params.id.toLong()) : new IntegrationProperty()
        integrationProperty.properties = params
        boolean success = false
        try {
            success = integrationService.saveIntegration(integrationProperty)
        } catch (Exception e) {
            println e.getMessage()
        }
        if (success) {
            flash.success = true
            flash.message = "Instance has been updated Successfully"
        } else {
            flash.success = false
            flash.message = "Instance cannot be updated. Check if the entered value is valid."
        }
        if (success) {
            redirect(action: "view")
        } else {
            render(view: "/integration/listView", model: [integrationProperties: params])
        }
    }

    def delete() {
        Boolean result = integrationService.deleteIntegration(params.id.toLong())
        if (result) {
            flash.success = true
            flash.message = "Integration property has been deleted Successfully"
        } else {
            flash.success = false
            flash.message = "Integration property cannot  delete"
        }
        redirect(action: "view")
    }

}
