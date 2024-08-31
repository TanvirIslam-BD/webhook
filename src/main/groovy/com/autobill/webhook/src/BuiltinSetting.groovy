package com.autobill.webhook.src

enum BuiltinSetting {
    TOKEN_EXPIRE_SECOND("3600")

    BuiltinSetting(String defaultValue) {
        this.defaultValue = defaultValue
    }

    String defaultValue

    String getDefaultValue() {
        return defaultValue
    }

    String getName(){
        return this.toString()
    }
}