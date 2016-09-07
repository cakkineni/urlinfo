package com.ca.urlinfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by cakkinen on 9/6/16.
 */
public class UrlChecker {

    public static boolean isMalware(String url) {
        if (url == null || url == "") {
            return false;
        } else {
            //TODO: Move to config.
            JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, Protocol.DEFAULT_TIMEOUT);
            String value = "";
            Jedis redis = null;
            try {
                redis = pool.getResource();
                value = redis.get("foo");
            } catch (JedisConnectionException e) {
                if (redis != null) {
                    pool.returnBrokenResource(redis);
                    redis = null;
                }
                throw e;
            } finally {
                if (redis != null) {
                    pool.returnResource(redis);
                }
            }
            if (value.equals("bar")) return true;
        }
        return false;

    }

}
