package com.ca.service;

import com.ca.urlinfo.UrlChecker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;

@RestController
public class UrlInfoService {
    static UrlChecker urlChecker = new UrlChecker();

    @RequestMapping(path = "/urlinfo", method = RequestMethod.GET)
    public String isMalwareUrl(@RequestParam(value = "url") String url, HttpServletResponse response) {
        boolean isMalware;
        try {
            isMalware = urlChecker.isMalware(url);
        } catch (URISyntaxException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return "";
        }
        if (isMalware) response.setStatus(HttpStatus.OK.value());
        else response.setStatus(HttpStatus.NO_CONTENT.value());
        return isMalware ? "malware" : "";
    }
}
