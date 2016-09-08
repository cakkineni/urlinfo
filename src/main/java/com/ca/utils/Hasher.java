package com.ca.utils;

import java.math.BigInteger;

/**
 * Created by cakkinen on 9/7/16.
 */
public class Hasher {
    public static BigInteger getStringHash(String str) {
        BigInteger bigInt = new BigInteger(str.getBytes());
        return bigInt;
    }
}
