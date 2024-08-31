package webhook

import com.autobill.webhook.Setting
import com.autobill.webhook.src.BuiltinSetting
import grails.gorm.transactions.Transactional

@Transactional
class SettingService {
    def initialize() {
        Setting.withTransaction {
            if(Setting.findAll().size() == 0){
                BuiltinSetting.values().each {
                    save(new Setting(name: it.toString(), value: it.defaultValue))
                }
            }
        }
    }

    boolean save(Setting setting) {
        setting.save()
        if(setting.hasErrors()){
            log.error(setting.errors.getAllErrors().get(0).toString())
            return false
        }
        return true
    }

    Setting getSetting(BuiltinSetting builtinSetting) {
        return Setting.findByName(builtinSetting.getName())
    }

    String getSettingValue(BuiltinSetting builtinSetting) {
        Setting setting = getSetting(builtinSetting)
        if(setting == null){
            return null
        }
        return setting.value
    }

    boolean setSetting(BuiltinSetting builtinSetting, String value) {
        Setting setting = Setting.findByName(builtinSetting.getName())
        setting.setValue(value)
        try{
            return save(setting)
        }catch(Exception e){
            log.error(e.message)
            return false
        }
    }

    Integer getTokenExpireSecond(){
        String value = getSettingValue(BuiltinSetting.TOKEN_EXPIRE_SECOND)
        return Integer.parseInt(value)
    }
}
