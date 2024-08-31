package com.autobill.webhook.services

import com.autobill.webhook.src.resolver.RequestUrlResolver
import grails.gorm.transactions.Transactional

@Transactional
class OrderRequestUrlResolverService implements RequestUrlResolver{

    public static final CREATE_ORDER_ENDPOINT = "/api/v2/autoBillOrder/create"

    @Override
    String resolveFullUrl(String action) {
        String url = ""
        switch (action) {
            case "create":
                url = CREATE_ORDER_ENDPOINT
                break
            default:
                break
        }
        return url
    }
}
