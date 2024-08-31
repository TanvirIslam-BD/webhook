package com.autobill.webhook

import com.autobill.webhook.src.TokenUtil
import com.autobill.webhook.src.init.ApplicationInit
import com.djamware.securepage.Role
import com.djamware.securepage.User
import com.djamware.securepage.UserRole
import webhook.SettingService
import webhook.TokenService

class BootStrap {
    TokenService tokenService
    SettingService settingService

    def init = { servletContext ->
        settingService.initialize()
        ApplicationInit.init()
        initApp()
        initUser()
    }

    def destroy = {
    }

    def initUser(){
        if(User.findAll().size() == 0){
            def adminRole
            Role.withTransaction { rl ->
                adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
            }

            def testUser
            User.withTransaction { us ->
                testUser = new User(username: 'admin', password: 'London88').save(flush: true)
            }

            UserRole.create testUser, adminRole

            UserRole.withTransaction { uRole ->
                UserRole.withSession {
                    it.flush()
                    it.clear()
                }
            }
        }
    }

    def void initApp() {
        if(App.findAll().size() == 0){
            App app = new App()
            app.name = App.PROVISIONING_APP_NAME
            app.redirectUri = "http://ps.bob.com/api/callbackWebhook"
            app.clientId = TokenUtil.generateToken()
            app.clientSecret = TokenUtil.generateToken()
            app.save()
            tokenService.createNewAccessToken(app)
        }
    }
}
