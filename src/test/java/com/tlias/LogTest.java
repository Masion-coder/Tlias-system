package com.tlias;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog() {
        // System.out.println(LocalDateTime.now() + ":开始i计算...");
        log.debug(LocalDateTime.now() + ":开始i计算...");
        int sum = 0;
        int[] nums = { 1, 5, 3, 2, 1, 4, 5, 6, 7, 4, 34, 2, 23 };
        for (int num : nums) {
            sum += num;
            // System.out.println("i计算结果为：" + sum);
            // System.out.println(LocalDateTime.now() + "结束i计算...");
            log.info("i计算结果为：" + sum);
            log.debug(LocalDateTime.now() + "结束i计算...");
        }
    }
}
