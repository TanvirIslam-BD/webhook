package com.autobill.webhook.src.Constant

class Constants {
    static Map APPLICATIONS = [
            ZOHO: "zoho",
            PAYPAL: "paypal",
            HUBSPOT: "hubspot",
            SALESFORCE: "salesforce",
            PAYMENT_ACCOUNT: "paymentAccount",
            EV_PAYMENT_ACCOUNT: "evPaymentAccount",
            DOCU_SIGN: "docuSign",
    ]

    static Map REQUEST_IDENTIFIER = [
            HUBSPOT: "portalId",
            ZOHO: "organization-id",
            SALESFORCE: "OrganizationId",
            PAYMENT_ACCOUNT: "PPWebhookID",
            EV_PAYMENT_ACCOUNT: "PPAutoBillID",
            DOCU_SIGN: "docuSign",
    ]
}
