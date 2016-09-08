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
        //Need to mock redis. Cannot use the actual data source.
        m =  Mockito.mock(Model.class);
        checker = new UrlChecker(m);

        final String key = "malware-sample.com";
        final String key1 = "malware-sample.com:8080";
        Mockito.when(m.checkKey(key)).thenReturn(true);
        Mockito.when(m.checkKey(key1)).thenReturn(true);


//        try {
//            BufferedReader br = new BufferedReader(new FileReader(UrlCheckerTest.class.getClassLoader().getResource("samples").toURI().getRawPath()));
//            String line;
//            while ((line = br.readLine()) != null) {
//               m.setKey(Url.getHost(line));
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    @Test
    public void whenNull() throws Exception {
        assertEquals(false, checker.isMalware(null));
    }

    @Test
    public void whenEmpty() throws Exception {
        assertEquals(false, checker.isMalware(""));
    }

    @Test
    public void whenNotFound() throws Exception {
        assertEquals(false, checker.isMalware("http://www.google.com"));
    }

    @Test
    public void whenFoundHttpNoPort() throws Exception {
        assertEquals(true, checker.isMalware("http://www.malware-sample.com"));
    }

    @Test
    public void whenFoundHttpsNoPort() throws Exception {
        assertEquals(true, checker.isMalware("https://www.malware-sample.com"));
    }

    @Test
    public void whenFoundHttpWithPort() throws Exception {
        assertEquals(true, checker.isMalware("https://www.malware-sample.com:8080"));
    }

    @Test
    public void whenFoundHttpsWithPort() throws Exception {
        assertEquals(true, checker.isMalware("https://www.malware-sample.com"));
    }

    @Test
    public void whenFoundNoHttpNoPort() throws Exception {
        assertEquals(true, checker.isMalware("www.malware-sample.com"));
    }

    @Test
    public void whenFoundNoHttpWithPort() throws Exception {
        assertEquals(true, checker.isMalware("www.malware-sample.com:8080"));
    }

}