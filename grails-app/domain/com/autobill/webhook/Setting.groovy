package com.autobill.webhook

class Setting {
    Integer id
    String name
    String value

    static constraints = {
    }

    static String getValue(String name){
        Setting setting = Setting.findWhere(name: name)
        if(setting == null){
            return null
        }
        return setting.value
    }
}
