package com.ca.urlinfo;

import com.ca.utils.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cakkinen on 9/6/16.
 */
@Component
public class UrlChecker {

    @Autowired
    Model model;

    @Autowired
    public UrlChecker(Model model) {
        if (model == null)
            throw new RuntimeException("Model cannot be null");
        this.model = model;
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
