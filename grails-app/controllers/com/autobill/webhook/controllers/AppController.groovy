package com.autobill.webhook.controllers

import com.autobill.webhook.App
import grails.plugin.springsecurity.annotation.Secured
import webhook.TokenService

@Secured('ROLE_ADMIN')
class AppController {
    TokenService tokenService

    def save(){
        App.withTransaction {
            App app = App.findById(params.int("id"))
            app.properties = params
            app.save()
            if(app.hasErrors()){
                flash.success = false
                flash.message = app.errors.getAllErrors().get(0).toString()
            }else{
                flash.success = true
                flash.message = "App saved"
            }
        }
        redirect(action:"edit")
    }

    def refreshAccessToken(){
        App app = App.findById(params.int("id"))
        tokenService.createNewAccessToken(app)
        flash.success = true
        flash.message = "Access token refreshed"
        redirect(action:"index")
    }

    def index() {
        List<App> apps = App.findAll(sort:"id",order:"asc")
        List<Map> maps = []
        for(App app : apps){
            Map map = [:]
            app.properties.each{ i, j->
                map[i] = j
            }
            map.id = app.id
            map.accessToken = tokenService.getLastAccessToken(app).token
            map.refreshToken = tokenService.getLastRefreshToken(app).token
            maps.add(map)
        }
        [apps: maps]
    }

    def delete(){
        App.withTransaction {
            App app = App.findById(params.int("id"))
            tokenService.deleteAllAccessTokens(app)
            app.delete()
        }
        flash.success = true
        flash.message = "Client deleted"
        redirect(action:"index")
    }
}
