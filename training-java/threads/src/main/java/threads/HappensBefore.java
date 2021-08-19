package me.stone.training.platform.training.java.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/29 15:39
 */
@SuppressWarnings("ALL")
public class HappensBefore {

    static int i = 1;
    static volatile boolean flag = false;
    static int y = 1;

    static void testJoinRule() throws InterruptedException {
        AtomicInteger x = new AtomicInteger(1);
        final Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x.set(2);
        });
        t.start();
        t.join();
        assert x.get() == 2;
    }

    static void testStartRule() {
        AtomicInteger x = new AtomicInteger(1);
        final Thread thread = new Thread(() -> {
            assert x.get() == 2;
        });
        x.set(2);
        thread.start();
    }

    static void testVolatileRuleReader() {
        i = 2;
        flag = true;
    }

    static void testVolatileRuleWriter() {
        if (flag) {
            assert i == 2;
        }
    }

    static void testMonitorRule() {
        synchronized (Object.class) {
            if (y == 1) {
                y = 2;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testStartRule();
    }
}
