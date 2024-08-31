package com.autobill.webhook.services


import com.autobill.webhook.src.resolver.RequestUrlResolver

class AccountRequestUrlResolverService implements RequestUrlResolver {

    public static final CREATE_ACCOUNT_ENDPOINT = "/api/v2/autoBillCustomer/create"
    public static final UPDATE_ACCOUNT_ENDPOINT = "/api/v2/autoBillCustomer/update"
    public static final DELETE_ACCOUNT_ENDPOINT = "/api/v2/autoBillCustomer/delete"

    @Override
    String resolveFullUrl(String action) {
        String url = ""
        switch (action) {
            case "create":
                url = CREATE_ACCOUNT_ENDPOINT
                break
            case "update":
                url = UPDATE_ACCOUNT_ENDPOINT
                break
            case "delete":
                url = DELETE_ACCOUNT_ENDPOINT
                break
            default:
                break
        }
        return url
    }
}
