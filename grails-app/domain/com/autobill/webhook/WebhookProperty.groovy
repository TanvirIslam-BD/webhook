package com.autobill.webhook

class WebhookProperty {

    Integer id
    String entityName
    String actionName
    String requestInstanceId
    String method
    String data
    String preparedData
    String queryString
    String fullUrl
    String response
    Integer retryCount = 0
    String delay
    Boolean isSuccess = false
    Boolean isCurrentlyRunning = false

    Date created
    Date updated


    static constraints = {
        method nullable: true
        queryString nullable: true
        data nullable: true
        fullUrl nullable: true
        response nullable: true
        retryCount nullable: true
        delay nullable: true
        isSuccess nullable: true
        isCurrentlyRunning nullable: false
        created nullable: true
        updated nullable: true
        preparedData nullable: true
    }

    static mapping = {
        version false
        data type: "text"
        response type: "text"
        preparedData type: "text"
    }

    def beforeValidate() {
        if (!this.created) {
            this.created = new Date()
        }
        if (!this.updated) {
            this.updated = new Date()
        }
    }

    def beforeUpdate() {
        this.updated = new Date()
    }

}
