package webhook

import com.autobill.webhook.AccessToken
import com.autobill.webhook.App
import com.autobill.webhook.RefreshToken
import com.autobill.webhook.src.TokenUtil
import grails.gorm.transactions.Transactional

@Transactional
class TokenService {
    SettingService settingService

    AccessToken getLastAccessToken(App app){
        AccessToken accessToken = null
        accessToken = AccessToken.findAllByApp(app,[sort: "created", order: "desc"]).get(0)
        return accessToken
    }

    RefreshToken getRefreshToken(AccessToken accessToken){
        RefreshToken refreshToken = RefreshToken.findAllByAccessToken(accessToken, [sort:"created", order:"desc"]).get(0)
        return refreshToken
    }

    def deleteAllAccessTokens(App app){
        for(AccessToken accessToken : AccessToken.find("From AccessToken where app_id=${app.id}")){
            accessToken = AccessToken.findById(accessToken.id)
            deleteAllRefreshTokens(accessToken)
            accessToken.delete()
        }
    }

    def deleteAllRefreshTokens(AccessToken accessToken){
        for(RefreshToken refreshToken : RefreshToken.findAllByAccessToken(accessToken)){
            refreshToken = RefreshToken.findById(refreshToken.id)
            refreshToken.delete()
        }
    }

    AccessToken createNewAccessToken(App app){
        deleteAllAccessTokens(app)
        AccessToken accessToken = new AccessToken()

        accessToken.token = TokenUtil.generateToken()
        accessToken.expireSeconds = settingService.getTokenExpireSecond()
        accessToken.created = new Date()
        accessToken.app = app
        accessToken.save()

        RefreshToken refreshToken = new RefreshToken()
        refreshToken.token = TokenUtil.generateToken()
        refreshToken.accessToken = accessToken
        refreshToken.created = new Date()
        refreshToken.save()

        return accessToken
    }

    def createNewRefreshToken(AccessToken accessToken) {
        deleteAllRefreshTokens(accessToken)
        RefreshToken refreshToken = new RefreshToken()
        refreshToken.token = TokenUtil.generateToken()
        refreshToken.accessToken = accessToken
        refreshToken.created = new Date()
        refreshToken.save()
    }

    def boolean expired(AccessToken accessToken) {
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(accessToken.created)
        calendar.add(Calendar.SECOND, accessToken.expireSeconds)
        Date expireDate =  calendar.getTime()
        return new Date().compareTo(expireDate) > 0;
    }

    RefreshToken getLastRefreshToken(App app){
        return getRefreshToken(getLastAccessToken(app))
    }
}

