package com.autobill.webhook.src.util

class AppUtil {
    static String concatUrl(String firstURL, String secondURL){
        if (firstURL.substring(firstURL.length() - 1, firstURL.length()) == "/")
        {
            firstURL = firstURL.substring(0, firstURL.length() - 1)
        }

        if (secondURL.substring(0, 1) == "/")
        {
            secondURL = secondURL.substring(1)
        }

        return firstURL + "/" + secondURL
    }
}
