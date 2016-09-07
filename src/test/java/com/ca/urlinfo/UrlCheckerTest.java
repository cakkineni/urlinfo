package com.ca.urlinfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by cakkinen on 9/6/16.
 */
public class UrlCheckerTest {

    @Test
    public void whenNull() throws Exception {
        assertEquals(false, UrlChecker.isMalware(null));
    }

    @Test
    public void whenEmpty() throws Exception {
        assertEquals(false, UrlChecker.isMalware(""));
    }

    @Test
    public void whenNotFound() throws Exception {
        assertEquals(false, UrlChecker.isMalware("http://www.google.com"));
    }

    @Test
    public void whenFoundHttpNoPort() throws Exception {
        assertEquals(true, UrlChecker.isMalware("http://www.malware-sample.com"));
    }

    @Test
    public void whenFoundHttpsNoPort() throws Exception {
        assertEquals(true, UrlChecker.isMalware("https://www.malware-sample.com"));
    }

    @Test
    public void whenFoundHttpWithPort() throws Exception {
        assertEquals(true, UrlChecker.isMalware("https://www.malware-sample.com:8080"));
    }

    @Test
    public void whenFoundHttpsWithPort() throws Exception {
        assertEquals(true, UrlChecker.isMalware("https://www.malware-sample.com"));
    }

    @Test
    public void whenFoundNoHttpNoPort() throws Exception {
        assertEquals(true, UrlChecker.isMalware("www.malware-sample.com"));
    }

    @Test
    public void whenFoundNoHttpWithPort() throws Exception {
        assertEquals(true, UrlChecker.isMalware("www.malware-sample.com:8080"));
    }

}