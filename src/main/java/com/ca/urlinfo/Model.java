package com.ca.urlinfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by cakkinen on 9/7/16.
 */
public class Model {

    static JedisPool jedisPool;

    private static synchronized Jedis getJedisConnection() {
        try {
            if (jedisPool == null) {
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(1000);
                config.setMaxIdle(10);
                config.setMinIdle(1);
                config.setMaxWaitMillis(30000);
                jedisPool = new JedisPool(config, "localhost", 6379);
            }
            return jedisPool.getResource();
        } catch (JedisConnectionException e) {
            throw e;
        }
    }


    public boolean checkKey(String key) {
        Jedis redis = null;
        try {
            redis = getJedisConnection();
            String bucketId = key.substring(0, 2);
            boolean val = redis.hexists("BID:" + bucketId, key);
            return val;
        } catch (JedisConnectionException e) {
            throw e;
        } finally {
            if (redis != null)
                redis.close();
        }
    }

    public boolean setKey(String key) {
        if (key != null && key.contains(".") && key.length() >= 3) {
            Jedis redis = null;
            try {
                String bucketId = key.substring(0, 2);
                redis = getJedisConnection();
                redis.hset("BID:" + bucketId, key, "");
                return true;
            } catch (JedisConnectionException e) {
                throw e;
            } finally {
                if (redis != null)
                    redis.close();
            }

        }
        return false;
    }

    public boolean removeKey(String key) {
        try {
            String bucketId = key.substring(0, 2);
            Jedis redis = getJedisConnection();
            redis.hdel("BID:" + bucketId, key);
            return true;
        } catch (JedisConnectionException e) {
            throw e;
        }
    }
}
