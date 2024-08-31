package com.autobill.webhook.controllers

import com.djamware.securepage.CustomUserDetails
import com.djamware.securepage.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.DelegatingPasswordEncoder

@Secured('ROLE_ADMIN')
class UserController {
    @Autowired
    @Lazy
    SpringSecurityService springSecurityService
    def index() { }

    def changePassword(){

    }

    def doChangePassword(){
        User.withTransaction {
            CustomUserDetails customUserDetails = springSecurityService.principal
            User user = User.findById(customUserDetails.id)
            DelegatingPasswordEncoder delegatingPasswordEncoder = springSecurityService.getPasswordEncoder()

            String oldPassword = params.oldPassword
            String newPassword = params.newPassword

            if(oldPassword == null || newPassword == null){
                flash.success = false
                flash.message = "Please enter the information properly"
                redirect(action : "changePassword")
                return
            }

            boolean result = delegatingPasswordEncoder.matches(oldPassword,user.password)

            if(!result){
                flash.success = false
                flash.message = "Old password doesn't match"
                redirect(action : "changePassword")
                return
            }

            user.password = newPassword
            user.save()

            flash.success = true
            flash.message = "Password changed"
            redirect(uri:"/")
        }
    }
}