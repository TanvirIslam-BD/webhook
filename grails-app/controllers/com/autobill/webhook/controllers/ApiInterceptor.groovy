package com.autobill.webhook.controllers

import com.autobill.webhook.AccessToken
import grails.converters.JSON
import webhook.TokenService


class ApiInterceptor {
    TokenService tokenService
    public ApiInterceptor(){
        match(controller :"api").except(action: "tokenRenew")
    }

    boolean before() {
        println "Received request ${request.requestURI}"
        def json = [
                "error" : ""
        ]
        String accessToken = request.getHeader("authorization")
        if(accessToken == null){
            response.setStatus(400)
            json.error = "No access token provided"
            render(json as JSON)
            return false
        }

        if(!accessToken.startsWith("Bearer ")){
            response.setStatus(400)
            json.error = "Invalid access token format"
            render(json as JSON)
            return false
        }

        accessToken = accessToken.substring("Bearer ".length())

        AccessToken accessTokenDomain = AccessToken.findByToken(accessToken)
        if(accessTokenDomain == null){
            response.setStatus(400)
            json.error = "Invalid access token"
            render(json as JSON)
            return false
        }
        if(tokenService.expired(accessTokenDomain)){
            response.setStatus(401)
            json.error = "Token expired"
            render(json as JSON)
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}

