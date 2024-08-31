package com.autobill.webhook.controllers

import com.autobill.webhook.services.WebhookPropertyService
import com.autobill.webhook.src.cachedrequest.CachedBodyHttpServletRequest

import grails.converters.JSON

class ApplicationController {
    WebhookPropertyService webhookPropertyService

    def webhook() {

        String webhookURL = params.handler
        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request)
        boolean result = webhookPropertyService.saveWebhookProperty(webhookURL, cachedBodyHttpServletRequest)

        if (result) {
            render(
                    status: 200,
                    contentType: "application/json",
                    text: [
                            "message": "Webhook received successfully"
                    ] as JSON
            )
        } else {
            render(
                    status: 404,
                    contentType: "application/json",
                    text: [
                            "isSuccess": false,
                            "error"    : [
                                    "code"   : "404",
                                    "message": "No Mapping found"
                            ]
                    ] as JSON
            )
        }
    }


}
