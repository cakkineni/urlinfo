package com.ca.urlinfo;

import com.ca.utils.Url;

/**
 * Created by cakkinen on 9/6/16.
 */
public class UrlChecker {

    Model model;

    public UrlChecker(Model model) {
        if (model == null)
            throw new RuntimeException("Model cannot be null");
        this.model = model;
    }

    public UrlChecker() {
        this.model = new Model();
    }

    public boolean isMalware(String url, String fullUrl) {
        if (url == null || url.isEmpty() || fullUrl == null || fullUrl.isEmpty()) {
            return false;
        } else {
            String domainName = Url.getHost(url);
            return model.checkKey(domainName, fullUrl);
        }
    }

}
