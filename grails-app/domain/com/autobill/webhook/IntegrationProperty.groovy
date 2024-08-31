package com.autobill.webhook

class IntegrationProperty {
    Long id

    String externalInstanceId
    String wcInstanceName
    String wcInstancePath

    static constraints = {
        wcInstanceName nullable: true
    }
}
