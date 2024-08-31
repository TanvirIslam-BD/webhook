package com.util

/**
 * This is a simple token based M2M authorization system
 * @author: WebCommander
 * @Since: 2022-02-23
 */

class SimpleEncryptor {

    long target

    public SimpleEncryptor(long info) {
        this.target = info
    }

    public void setTarget(long info) {
        this.target = info;
    }

    public long getCipher() {
        return ((target & 0xffff0000ffff0000l) >> 16) | ((target & 0x0000ffff0000ffffl) << 16)
    }

    public long getInfo() {
        return ((target & 0xffff0000ffff0000l) >> 16) | ((target & 0x0000ffff0000ffffl) << 16)
    }

    public static String getToken(){
        SimpleEncryptor simpleEncryptor = new SimpleEncryptor(System.currentTimeMillis())
        return simpleEncryptor.getCipher() + ""
    }
}
