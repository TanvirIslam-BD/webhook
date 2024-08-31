package com.autobill.webhook.src

import com.autobill.webhook.src.util.AppUtil
import com.autobill.webhook.src.util.Configurations

class Instance {
    private String port
    private String code
    private String webhookData
    private String queryString
    private String fullUrl
    private String fullUrlWithPort
    private String fullUrlWithWebhook
    private String fullUrlWithPortAndWebhook

    Instance(String port, String code, String webhookUrl, String webhookData, String queryString) {
        this.port = port
        this.code = code
        this.fullUrl = "http://$code${Configurations.getConfig(Configurations.INSTANCE_URL_POSTFIX)}"
        this.fullUrlWithPort = "$fullUrl:$port"
        this.fullUrlWithWebhook = AppUtil.concatUrl(fullUrl, webhookUrl)
        this.fullUrlWithPortAndWebhook = AppUtil.concatUrl(fullUrlWithPort, webhookUrl)
        this.webhookData = webhookData
        this.queryString = queryString
    }

    String getPort() {
        return port
    }

    String getCode() {
        return code
    }

    String getFullUrl() {
        return fullUrl
    }

    String getFullUrlWithPort() {
        return fullUrlWithPort
    }

    String getFullUrlWithWebhook() {
        return fullUrlWithWebhook
    }

    String getFullUrlWithPortAndWebhook() {
        return fullUrlWithPortAndWebhook
    }

    String getWebhookData() {
        return webhookData
    }

    String getQueryString() {
        return queryString
    }
}
