package com.autobill.webhook.controllers

import com.autobill.webhook.AccessToken
import com.autobill.webhook.App
import com.autobill.webhook.RefreshToken
import com.autobill.webhook.src.TokenUtil
import grails.converters.JSON
import org.springframework.security.access.annotation.Secured
import webhook.TokenService

@Secured('permitAll')
class ApiController {
    TokenService tokenService
    def tokenRenew(){
        def theResponse = [
                "access_token":"",
                "token_type":"bearer",
                "expires_in":0,
                "refresh_token":"",
                "scope":"create"
        ]

        def theError = ["error" : ""]

        def json = request.getJSON();

        if(json.grant_type == null || !json.grant_type.equals("refresh_token")){
            response.setStatus(400)
            theError.error = "Invalid grant type"
            render theError as JSON
            return
        }

        if(json.client_id == null){
            response.setStatus(400)
            theError.error = "Client ID not provided"
            render theError as JSON
            return
        }

        App app = App.findByClientId(json.client_id)

        if(app == null){
            response.setStatus(400)
            theError.error = "Invalid Client ID"
            render theError as JSON
            return
        }

        if(json.client_secret == null){
            response.setStatus(400)
            theError.error = "Client Secret not provided"
            render theError as JSON
            return
        }

        if(!json.client_secret.equals(app.clientSecret)){
            response.setStatus(400)
            theError.error = "Invalid Client Secret"
            render theError as JSON
            return
        }

        if(json.redirect_uri == null){
            response.setStatus(400)
            theError.error = "Redirect URI not provided"
            render theError as JSON
            return
        }

        if(!json.redirect_uri.equals(app.redirectUri)){
            response.setStatus(400)
            theError.error = "Invalid Redirect URI"
            render theError as JSON
            return
        }

        if(json.refresh_token == null){
            response.setStatus(400)
            theError.error = "Refresh Token not provided"
            render theError as JSON
            return
        }

        RefreshToken refreshToken = tokenService.getLastRefreshToken(app)
        if(!json.refresh_token.equals(refreshToken.token)){
            response.setStatus(400)
            theError.error = "Invalid refresh token"
            render theError as JSON
            return
        }

        AccessToken newAccessToken = tokenService.createNewAccessToken(app)
        RefreshToken newRefreshToken = tokenService.getRefreshToken(newAccessToken)

        theResponse.access_token = newAccessToken.token
        theResponse.refresh_token = newRefreshToken.token
        theResponse.expires_in = newAccessToken.expireSeconds

        render theResponse as JSON

    }

    def test(){
        def response = ["success" :true]
        render response as JSON
    }

    def create() {
        String instanceID = params.instanceID
        App app = null

        if (App.findAllByName(instanceID).size() == 0) {
            app = new App()
            app.name = instanceID
            app.redirectUri = "http://ps.tom.com/callback/client/${instanceID}"
            app.clientId = TokenUtil.generateToken()
            app.clientSecret = TokenUtil.generateToken()
            app.save()
        }
        else {
            app = App.findByName(instanceID)
        }

        tokenService.createNewAccessToken(app)

        AccessToken accessToken = tokenService.getLastAccessToken(app)
        RefreshToken refreshToken = tokenService.getLastRefreshToken(app)
        Map response = [:]
        response.put("clientId", app.clientId)
        response.put("clientSecret", app.clientSecret)
        response.put("redirectUri", app.redirectUri)
        response.put("accessToken", accessToken.token)
        response.put("refreshToken", refreshToken.token)
        response.put("expiry", accessToken.expireSeconds)

        render response as JSON
    }

    def remove() {
        Map responseJson = ["isSuccess":true, "message":"Removed client"]
        String instanceID = params.instanceID
        App.withTransaction {
            App app = App.findByName(instanceID)

            if (app == null) {
                responseJson.isSuccess = false
                responseJson.message = "Client not found"
                render response as JSON
                return
            }
            tokenService.deleteAllAccessTokens(app)
            app.delete()
        }

        render responseJson as JSON
    }
}

