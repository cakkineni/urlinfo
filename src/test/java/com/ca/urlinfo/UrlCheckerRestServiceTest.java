package com.ca.urlinfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by cakkinen on 9/8/16.
 */
public class UrlCheckerRestServiceTest {

    Model m;
    UrlChecker checker;

    @Before
    public void runOnceBeforeClass() {
        //Need to mock redis. Cannot use the actual data source.
        m =  Mockito.mock(Model.class);
        checker = new UrlChecker(m);

        final String key = "malware-sample.com";
        final String key1 = "malware-sample.com:8080";
        Mockito.when(m.checkKey(key)).thenReturn(true);
        Mockito.when(m.checkKey(key1)).thenReturn(true);
    }

    @Test
    public void whenNull() throws Exception {
    }

}
