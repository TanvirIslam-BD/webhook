package com.autobill.webhook.src.handler

import com.autobill.webhook.src.handler.hubspot.HubSpotHandler
import com.autobill.webhook.src.handler.paymentaccount.EvPaymentAccountHandler
import com.autobill.webhook.src.handler.paypal.PayPalConnectHandler
import com.autobill.webhook.src.handler.paypal.PayPalPaymentDoneHandler
import com.autobill.webhook.src.handler.salesforce.SalesForceHandler
import com.autobill.webhook.src.handler.paymentaccount.PaymentAccountHandler
import com.autobill.webhook.src.handler.zoho.ZohoHandler
import com.autobill.webhook.src.handler.docusign.DocuSignHandler

enum WebhookHandlerMapping {
    ZOHO_CONNECT("zoho","zoho", ZohoHandler.class),
    HUBSPOT_CONNECT("hubspot","hubspot", HubSpotHandler.class),
    PAYPAL_CONNECT("paypal", "paypal", PayPalConnectHandler.class),
    SALESFORCE_CONNECT("salesforce", "salesforce", SalesForceHandler.class),
    PAYMENT_ACCOUNT("paymentAccount", "paymentAccount", PaymentAccountHandler.class),
    EV_PAYMENT_ACCOUNT("evPaymentAccount", "evPaymentAccount", EvPaymentAccountHandler.class),
    PAYPAL_NOTIFY_PAYMENT_DONE("paypal-payment-done", "paypal/notifyPaymentDone", PayPalPaymentDoneHandler.class),
    DOCU_SIGN("docuSign", "docuSign", DocuSignHandler.class)

    String name
    String url
    Class<AutoBillHandler> handler

    WebhookHandlerMapping(String name, String url, Class<AutoBillHandler> handler){
        this.name = url
        this.url = url
        this.handler = handler
    }

    String getUrl() {
        return url
    }

    String getName() {
        return name
    }

    Class<AutoBillHandler> getHandler() {
        return handler
    }

    static AutoBillHandler getHandlerByURL(String url){
        url = url.substring(url.length() - 1).equalsIgnoreCase("/") ? url.substring(0, url.length() - 1) : url
         WebhookHandlerMapping webhookHandlerMapping = values().find{ it.getUrl().equalsIgnoreCase(url) }
        if(webhookHandlerMapping){
            return webhookHandlerMapping.getHandler().getDeclaredConstructor().newInstance()
        }
        return null
    }

    static AutoBillHandler getHandlerByName(String name){
         WebhookHandlerMapping webhookHandlerMapping = values().find{ it.getName().equalsIgnoreCase(name) }
        if(webhookHandlerMapping){
            return webhookHandlerMapping.getHandler().getDeclaredConstructor().newInstance()
        }
        return null
    }
}
