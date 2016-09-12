package com.ca.urlinfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by cakkinen on 9/6/16.
 */
public class UrlCheckerTest {

    Model m;
    UrlChecker checker;

    final String key = "malware-sample.com";

    final String urlHttpNoWwwNoPortNoQS = "http://www.malware-sample.com";
    final String urlNoHttpWwwNoPortNoQS = "www.malware-sample.com";
    final String urlNoHttpNoWwwNoPortNoQS = "malware-sample.com";
    final String urlHttpNoWwwNoPortQS = "http://www.malware-sample.com?test=123";
    final String urlHttpsPort = "https://www.malware-sample.com:8080";
    final String urlHttpPort = "http://www.malware-sample.com:8080";

    @Before
    public void runOnceBeforeClass() {
        m = Mockito.mock(Model.class);
        checker = new UrlChecker(m);

        Mockito.when(m.checkKey(key, urlHttpNoWwwNoPortNoQS)).thenReturn(true);
        Mockito.when(m.checkKey(key, urlNoHttpWwwNoPortNoQS)).thenReturn(true);
        Mockito.when(m.checkKey(key, urlNoHttpNoWwwNoPortNoQS)).thenReturn(true);
        Mockito.when(m.checkKey(key, urlHttpNoWwwNoPortQS)).thenReturn(true);
        Mockito.when(m.checkKey(key, urlHttpsPort)).thenReturn(true);
        Mockito.when(m.checkKey(key, urlHttpPort)).thenReturn(true);
    }

    @Test
    public void whenNull() throws Exception {
        assertFalse(checker.isMalware(null, null));
    }

    @Test
    public void whenEmpty() throws Exception {
        assertFalse(checker.isMalware("", ""));
    }

    @Test
    public void whenNotFound() throws Exception {
        assertFalse(checker.isMalware("google.com:80", "http://www.google.com"));
    }

    @Test
    public void whenUrlHttpNoWwwNoPortNoQS() throws Exception {
        assertTrue(checker.isMalware(key, urlHttpNoWwwNoPortNoQS));
    }

    @Test
    public void whenUrlNoHttpWwwNoPortNoQS() throws Exception {
        assertTrue(checker.isMalware(key, urlNoHttpWwwNoPortNoQS));
    }

    @Test
    public void whenUrlNoHttpNoWwwNoPortNoQS() throws Exception {
        assertTrue(checker.isMalware(key, urlNoHttpNoWwwNoPortNoQS));
    }

    @Test
    public void whenUrlHttpNoWwwNoPortQS() throws Exception {
        assertTrue(checker.isMalware(key, urlHttpNoWwwNoPortQS));
    }

    @Test
    public void whenUrlHttpsPort() throws Exception {
        assertTrue(checker.isMalware(key, urlHttpsPort));
    }

    @Test
    public void whenUrlHttpPort() throws Exception {
        assertTrue(checker.isMalware(key, urlHttpPort));
    }
}