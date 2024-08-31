package com.autobill.webhook

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'app', action:'index')
        "/connect/$handler**"(controller: 'application', action:'webhook')
        "/notify"(controller: 'integration', action:'listen', parseRequest: true, method: "POST")
        "/remove"(controller: 'integration', action:'remove', parseRequest: true, method: "POST")
        "/is-integrated"(controller: 'integration', action:'isIntegrated', parseRequest: true, method: "POST")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
