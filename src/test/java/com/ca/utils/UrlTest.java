package com.ca.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by cakkinen on 9/11/16.
 */
public class UrlTest {

    @Test
    public void whenEmptyHost() throws Exception {
        assertEquals("", Url.getHost(""));
    }

    @Test
    public void whenHttpWww() throws Exception {
        assertEquals("google.com", Url.getHost("http://www.google.com"));
    }

    @Test
    public void whenNoHttpNoWww() throws Exception {
        assertEquals("google.com", Url.getHost("google.com"));
    }

    @Test
    public void whenNoHttpNoWwwPort() throws Exception {
        assertEquals("google.com", Url.getHost("google.com:8080"));
    }

    @Test
    public void whenIPAddress() throws Exception {
        assertEquals("127.0.0.1", Url.getHost("127.0.0.1"));
    }
}