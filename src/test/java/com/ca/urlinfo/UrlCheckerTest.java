package com.ca.urlinfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by cakkinen on 9/6/16.
 */
public class UrlCheckerTest {

    Model m;
    UrlChecker checker;

    @Before
    public void runOnceBeforeClass() {
        final String key = "malware-sample.com";
        final String fullUrl = "http://www.malware-sample.com";
        final String fullUrl1 = "http://www.malware-sample.com:8080";
        final String fullUrl2 = "https://www.malware-sample.com";
        final String fullUrl3 = "https://www.malware-sample.com:8080";
        final String fullUrl4 = "www.malware-sample.com";
        final String fullUrl5 = "www.malware-sample.com:8080";


        m = Mockito.mock(Model.class);
        checker = new UrlChecker(m);

        Mockito.when(m.checkKey(key, fullUrl)).thenReturn(true);
        Mockito.when(m.checkKey(key, fullUrl1)).thenReturn(true);
        Mockito.when(m.checkKey(key, fullUrl2)).thenReturn(true);
        Mockito.when(m.checkKey(key, fullUrl3)).thenReturn(true);
        Mockito.when(m.checkKey(key, fullUrl4)).thenReturn(true);
        Mockito.when(m.checkKey(key, fullUrl5)).thenReturn(true);
    }

    @Test
    public void whenNull() throws Exception {
        assertEquals(false, checker.isMalware(null, null));
    }

    @Test
    public void whenEmpty() throws Exception {
        assertEquals(false, checker.isMalware("", ""));
    }

    @Test
    public void whenNotFound() throws Exception {
        assertEquals(false, checker.isMalware("google.com:80", "http://www.google.com"));
    }

    @Test
    public void whenFoundHttpNoPort() throws Exception {
        assertEquals(true, checker.isMalware("malware-sample.com", "http://www.malware-sample.com"));
    }

    @Test
    public void whenFoundHttpsNoPort() throws Exception {
        assertEquals(true, checker.isMalware("malware-sample.com", "https://www.malware-sample.com"));
    }

    @Test
    public void whenFoundHttpWithPort() throws Exception {
        assertEquals(true, checker.isMalware("malware-sample.com:8080", "https://www.malware-sample.com:8080"));
    }

    @Test
    public void whenFoundHttpsWithPort() throws Exception {
        assertEquals(true, checker.isMalware("malware-sample.com", "https://www.malware-sample.com"));
    }

    @Test
    public void whenFoundNoHttpNoPort() throws Exception {
        assertEquals(true, checker.isMalware("malware-sample.com", "www.malware-sample.com"));
    }

    @Test
    public void whenFoundNoHttpWithPort() throws Exception {
        assertEquals(true, checker.isMalware("malware-sample.com:8080", "www.malware-sample.com:8080"));
    }

}