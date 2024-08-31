package com.autobill.webhook

class RefreshToken {
    String token
    Date created

    static belongsTo = [
            accessToken : AccessToken
    ]

    static constraints = {
    }
}
