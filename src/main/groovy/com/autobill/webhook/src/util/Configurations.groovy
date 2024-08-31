package com.autobill.webhook.src.util


import grails.util.Holders

class Configurations {
    private static final String CONFIG_FILE_NAME = "config.properties"
    public static final String INSTANCE_URL_POSTFIX = "instance.url.postfix"

    private static Properties properties
    private static File configFile


    static String getConfig(String name, def defaultValue = null) {
        if (properties == null) {
            properties = new Properties()

            try {
                configFile = Holders.grailsApplication.mainContext.getResource("/WEB-INF/configuration/$CONFIG_FILE_NAME").getFile()
                InputStream stream = configFile.newInputStream()
                properties.load(stream)
                stream.close()
            }
            catch (Throwable k) {
                k.printStackTrace()
            }
        }

        return properties.getProperty(name) ?: defaultValue
    }

    static void setConfig(String name, String value) {
        properties.setProperty(name, value)
    }

    static storeConfig() {
        OutputStream stream = configFile.newOutputStream()
        properties.store(stream, null)
        stream.close()
    }
}