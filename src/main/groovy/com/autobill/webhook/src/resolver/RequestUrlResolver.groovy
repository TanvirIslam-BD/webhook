package com.autobill.webhook.src.resolver


interface RequestUrlResolver {
    String resolveFullUrl(String action)
}