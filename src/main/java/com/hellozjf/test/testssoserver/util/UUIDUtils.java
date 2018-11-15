package com.hellozjf.test.testssoserver.util;

import java.util.UUID;

/**
 * @author Jingfeng Zhou
 */
public class UUIDUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
