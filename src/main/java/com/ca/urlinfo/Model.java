package com.ca.urlinfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by cakkinen on 9/7/16.
 */
@Component
@PropertySource("classpath:/application.properties")
public class Model {

    static JedisPool jedisPool;

    @Value("${redis.host}")
    String redisHost = "localhost";

    @Value("${redis.port}")
    int redisPort = 6379;

    private synchronized Jedis getJedisConnection() {


        try {
            if (jedisPool == null) {
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(1000);
                config.setMaxIdle(10);
                config.setMinIdle(1);
                config.setMaxWaitMillis(30000);
                jedisPool = new JedisPool(config, redisHost, redisPort);
            }
            return jedisPool.getResource();
        } catch (JedisConnectionException e) {
            throw e;
        }
    }


    /**
     * Checks if a given key & value is stored in the redis database.
     * @param key The key to check for.
     * @param value The value to check for.
     * @return Returns true if the key/value combination exists in the data store, if not returns false. Returns false
     * for null params.
     */
    public boolean checkKey(String key, String value) {
        if (key != null && value != null) {
            Jedis redis = null;
            try {
                redis = getJedisConnection();
                return redis.sismember(key, value);
            } catch (JedisConnectionException e) {
                throw e;
            } finally {
                if (redis != null)
                    redis.close();
            }
        }
        return false;
    }

    /**
     * Adds the given key, value combination to the data store for eventual querying.
     * @param key The key to store.
     * @param value The value to store.
     * @return Returns true if the key/value combination is stored, false if key OR value is null.
     */
    public boolean setKey(String key, String value) {
        if (key != null && value != null) {
            Jedis redis = null;
            try {
                redis = getJedisConnection();
                redis.sadd(key, value);
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

    /**
     * Removes a given key/value pair from the data store.
     * @param key The key to remove.
     * @param value The value to remove.
     * @return Returns true if the value is removed, false for null key/value inputs.
     */
    public boolean removeKey(String key, String value) {
        if (key != null && value != null) {
            try {
                Jedis redis = getJedisConnection();
                redis.srem(key, value);
                return true;
            } catch (JedisConnectionException e) {
                throw e;
            }
        }
        return false;
    }
}
