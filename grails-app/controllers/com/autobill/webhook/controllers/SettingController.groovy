package com.autobill.webhook.controllers

import com.autobill.webhook.Setting
import grails.plugin.springsecurity.annotation.Secured
import webhook.SettingService

@Secured("ROLE_ADMIN")
class SettingController {
    SettingService settingService
    def index() {
        [settings : Setting.findAll(sort:"name")]
    }
    def edit(){
        [setting : Setting.findById(params.int("id"))]
    }
    def update(){
        Setting setting = Setting.findById(params.int("id"))
        setting.properties = params
        boolean success = false
        try {
            success = settingService.save(setting)
        } catch (Exception e) {
        }
        if(success){
            flash.success = true
            flash.message = "Setting updated"
        }else{
            flash.success = false
            flash.message = "Setting cannot be updated. Check if the entered value is valid."
        }
        redirect(action :"index")
    }
}
