package com.autobill.webhook.src.util

import com.util.SimpleEncryptor
import grails.converters.JSON
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.RequestMethod

class InstanceCaller {

    static Map cronJobWebhookCaller(String url, String requestMethod, String queryParams, String data) {
        try {

            if (StringUtils.hasText(queryParams)) {
                url += '?' + queryParams
            }


            URL baseUrl = new URL(url)
            HttpURLConnection connection = (HttpURLConnection) baseUrl.openConnection()

            println "API URL: " + url
            println "API METHOD: " + requestMethod
            println "API Request Data: " + data

            connection.setDoOutput(true)
            connection.setDoInput(true)
            connection.setRequestProperty("token", SimpleEncryptor.getToken())

            switch (requestMethod) {
                case RequestMethod.POST.toString():
                    connection.setRequestProperty("METHOD", RequestMethod.POST.toString())
                    connection.setRequestProperty("Content-Type", "application/json")
                    connection.getOutputStream().write(data.getBytes())
                    break
                case RequestMethod.PUT.toString():
                    connection.setRequestProperty("METHOD", RequestMethod.PUT.toString())
                    connection.setRequestProperty("Content-Type", "application/json")
                    connection.getOutputStream().write(data.getBytes())
                    break
                case RequestMethod.PATCH.toString():
                    connection.setRequestProperty("METHOD", RequestMethod.PATCH.toString())
                    connection.setRequestProperty("Content-Type", "application/json")
                    connection.getOutputStream().write(data.getBytes())
                    break
                case RequestMethod.DELETE.toString():
                    connection.setRequestProperty("METHOD", RequestMethod.DELETE.toString())
                    connection.setRequestProperty("Content-Type", "application/json")
                    connection.getOutputStream().write(data.getBytes())
                    break

                default:
                    connection.setRequestProperty("METHOD", RequestMethod.GET.toString())
                    break
            }
            def code = connection.getResponseCode()
            if (code >= 200 && code < 300) {
                String successResponse = connection.getInputStream().getText()
                println "Instance call success response: " + successResponse
                return [code: code, contentType: connection.getContentType(), text: successResponse]
            } else {
                String errorResponse = connection.getErrorStream().getText()
                println "Instance call error response: " + errorResponse
                return [code: code, contentType: connection.getContentType(), text: errorResponse]
            }

        } catch (Exception ignored) {
            printf("Error: " + ignored.toString() + "\n")
            ignored.printStackTrace()
            return [
                    code       : 500,
                    contentType: "application/json",
                    text       : [
                            "error": [
                                    "code"   : 500,
                                    "message": "Internal Server Error"
                            ]
                    ] as JSON
            ]
        }
    }
}
