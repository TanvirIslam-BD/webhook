package com.autobill.webhook

class App {
    public static final String PROVISIONING_APP_NAME="Provisioning";
    Integer id
    String name
    String redirectUri
    String clientId
    String clientSecret

    static constraints = {
        name unique: true, nullable: false
        redirectUri nullable: true
        clientId nullable: true
        clientSecret nullable: true
    }
}
