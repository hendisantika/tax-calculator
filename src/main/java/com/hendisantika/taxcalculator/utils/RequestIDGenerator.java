package com.hendisantika.taxcalculator.utils;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-04
 * Time: 05:38
 * To change this template use File | Settings | File Templates.
 */
public class RequestIDGenerator {
    private static UUID lastUUID = UUID.randomUUID();

    public RequestIDGenerator() {
    }

    public static String getID() {
        UUID randomUUID = UUID.randomUUID();
        if (randomUUID.compareTo(lastUUID) == 0) {
            randomUUID = UUID.randomUUID();
        }

        lastUUID = randomUUID;
        return randomUUID.toString();
    }
}
