package com.ca.utils;

/**
 * Created by cakkinen on 9/8/16.
 */
public class Url {
    public static String getHost(String url) {
        if (url == null || url.length() == 0)
            return "";

        int doubleSlash = url.indexOf("//");
        doubleSlash = doubleSlash == -1 ? 0 : doubleSlash + 2;

        int end = url.indexOf('/', doubleSlash);
        end = end >= 0 ? end : url.indexOf('/', doubleSlash);
        end = end >= 0 ? end : url.length();
        return url.substring(doubleSlash, end).replaceFirst("^www.*?\\.", "");
    }
}
