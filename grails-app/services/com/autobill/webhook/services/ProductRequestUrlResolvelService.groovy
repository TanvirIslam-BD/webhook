package com.autobill.webhook.services

import com.autobill.webhook.src.resolver.RequestUrlResolver
import grails.gorm.transactions.Transactional

@Transactional
class ProductRequestUrlResolvelService implements RequestUrlResolver{

    public static final CREATE_PRODUCT_ENDPOINT = "/api/v2/autoBillProduct/create"
    public static final UPDATE_PRODUCT_ENDPOINT = "/api/v2/autoBillProduct/update"
    public static final ADJUST_INVENTORY_PRODUCT_ENDPOINT = "/api/v2/autoBillProduct/adjustInventory"
    public static final DELETE_PRODUCT_ENDPOINT = "/api/v2/autoBillProduct/delete"

    @Override
    String resolveFullUrl(String action) {
        String url = ""
        switch (action) {
            case "create":
                url = CREATE_PRODUCT_ENDPOINT
                break
            case "update":
                url = UPDATE_PRODUCT_ENDPOINT
                break
            case "delete":
                url = DELETE_PRODUCT_ENDPOINT
                break
            case "adjustInventory":
                url = ADJUST_INVENTORY_PRODUCT_ENDPOINT
                break
            default:
                break
        }
        return url
    }
}
