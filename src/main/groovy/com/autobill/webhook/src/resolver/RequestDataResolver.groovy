package com.autobill.webhook.src.resolver

import com.autobill.webhook.WebhookProperty

interface RequestDataResolver {
    String prepareRequestData(WebhookProperty webhookProperty)
}