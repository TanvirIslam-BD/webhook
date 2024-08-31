package com.autobill.webhook.src.handler

import com.autobill.webhook.IntegrationProperty
import com.autobill.webhook.src.Instance
import com.autobill.webhook.src.cachedrequest.CachedBodyHttpServletRequest
import org.grails.web.json.JSONElement

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

abstract class AutoBillHandler {
    abstract List<Instance> determineInstance(CachedBodyHttpServletRequest httpServletRequest, HttpSession httpSession, Cookie[] cookies)

    abstract boolean matchProperty(IntegrationProperty integrationProperty, JSONElement jsonElement)
}
