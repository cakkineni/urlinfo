package com.ca.service;

import com.ca.urlinfo.UrlChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UrlInfoService {

    @Autowired
    UrlChecker urlChecker;

    @RequestMapping(path = "/urlinfo/1/{url}/{full_url}", method = RequestMethod.GET)
    public String isMalwareUrl(@PathVariable("url") String url, @PathVariable("full_url") String fullUrl, HttpServletResponse response) {
        if (url != null && !url.isEmpty() && fullUrl != null && !fullUrl.isEmpty()) {
            boolean isMalware = urlChecker.isMalware(url, fullUrl);
            if (isMalware) {
                response.setStatus(HttpStatus.OK.value());
                return "malware";
            }
        }
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return "";
    }
}
