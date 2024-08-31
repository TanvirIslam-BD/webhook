package com.autobill.webhook.services

import com.autobill.webhook.src.resolver.RequestUrlResolver

class ShipmentRequestUrlResolverService implements RequestUrlResolver{

    public static final CREATE_PAYMENT_ENDPOINT = "/api/v2/autoBillShipment/create"

    @Override
    String resolveFullUrl(String action) {
        String url = ""
        switch (action) {
            case "create":
                url = CREATE_PAYMENT_ENDPOINT
                break
            default:
                break
        }
        return url
    }
}
