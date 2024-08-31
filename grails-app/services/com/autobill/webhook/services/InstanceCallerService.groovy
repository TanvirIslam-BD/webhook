package com.autobill.webhook.services

import com.autobill.webhook.WebhookProperty
import com.autobill.webhook.src.util.ThreadRunnable
import groovy.transform.CompileStatic
import org.springframework.scheduling.annotation.Scheduled

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//@Slf4j
@CompileStatic
class InstanceCallerService {

    WebhookPropertyService webhookPropertyService

    static lazyInit = false

    @Scheduled(cron = "*/60 * * * * *")
    void instanceWebhookCaller() {
        ExecutorService executorService = Executors.newFixedThreadPool(20)
        println "Cron Job Running"
        List<WebhookProperty> webhookPropertyList = webhookPropertyService.findAll()

        if (!webhookPropertyList.isEmpty()) {
            for (WebhookProperty webhookProperty : (webhookPropertyList as List<WebhookProperty>)) {
                webhookPropertyService.setAsCurrentlyRunning(webhookProperty)
                if (!webhookProperty.fullUrl) {
                    webhookPropertyService.setFullUrl(webhookProperty)
                }
                if (!webhookProperty.preparedData) {
                    webhookPropertyService.prepareData(webhookProperty)
                }
                Runnable runnable = new ThreadRunnable(webhookProperty, webhookPropertyService)
                executorService.execute(runnable)
            }
        }
        executorService.shutdown()
    }

}
