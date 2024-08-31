package com.autobill.webhook.src.util

import com.autobill.webhook.WebhookProperty
import com.autobill.webhook.services.WebhookPropertyService

class ThreadRunnable implements Runnable {

    WebhookPropertyService webhookPropertyService
    WebhookProperty webhookProperty

    ThreadRunnable(WebhookProperty webhookProperty, WebhookPropertyService webhookPropertyService) {
        this.webhookProperty = webhookProperty
        this.webhookPropertyService = webhookPropertyService
    }

    @Override
    void run() {
        if (!webhookProperty.getIsSuccess() && webhookProperty.getRetryCount() <= 9) {
            println "Execution Start"
            try {
                if (webhookProperty.retryCount == 0) {
                    Map response = InstanceCaller.cronJobWebhookCaller(webhookProperty.fullUrl, webhookProperty.method, webhookProperty.queryString, webhookProperty.preparedData)
                    if (webhookPropertyService.isRequestSuccess(response.code)) {
                        println "Instance call success response: " + response.text
                        webhookPropertyService.saveRetryCount(webhookProperty, response.code, response.text.toString())
                    } else {
                        println "Instance call error response: " + response.text
                        webhookPropertyService.saveRetryCount(webhookProperty, response.code, response.text.toString())
                    }
                } else {
                    int sleepTime = webhookProperty.retryCount * 60000
                    Thread.sleep(sleepTime)
                    Map response = InstanceCaller.cronJobWebhookCaller(webhookProperty.fullUrl, webhookProperty.method, webhookProperty.queryString, webhookProperty.preparedData)
                    if (webhookPropertyService.isRequestSuccess(response.code)) {
                        println "Instance call success response: " + response.text
                        webhookPropertyService.saveRetryCount(webhookProperty, response.code, response.text.toString())
                    } else {
                        println "Instance call error response: " + response.text
                        webhookPropertyService.saveRetryCount(webhookProperty, response.code, response.text.toString())
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            println "Execute End"
        }
    }


}

