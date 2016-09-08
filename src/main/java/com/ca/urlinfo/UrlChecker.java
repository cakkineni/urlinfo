package com.ca.urlinfo;

import com.ca.utils.Url;

import java.net.URISyntaxException;

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

    public boolean isMalware(String url) throws URISyntaxException {
        if (url == null || url == "") {
            return false;
        } else {
            String domainName = Url.getHost(url);
            return model.checkKey(domainName);
        }
    }

}
