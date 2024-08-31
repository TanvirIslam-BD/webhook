package com.autobill.webhook

class AccessToken {
    String token
    Date created
    Integer expireSeconds

    static belongsTo = [
            app: App
    ]

    static constraints = {
        app nullable: true
    }
}
