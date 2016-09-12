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

    /**
     * Checks if a given URL exists in the malware database.
     * @param url The <domain name>:<port> which needs to be checked in the malware database.
     * @param fullUrl The full URL with querystring params that needs to be checked.
     * @return Returns true if the full URL exists in the malware database.
     *          Returns false for Bad URLS and safe URLs.
     */
    public boolean isMalware(String url, String fullUrl) {
        if (url == null || url.isEmpty() || fullUrl == null || fullUrl.isEmpty()) {
            return false;
        } else {
            String domainName = Url.getHost(url);
            return model.checkKey(domainName, fullUrl);
        }
    }

}
