package com.ca.urlinfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by cakkinen on 9/8/16.
 */

//TODO: Not sure if this is needed.
public class UrlCheckerRestServiceTest {

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
    }

}
