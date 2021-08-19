package me.stone.training.platform.training.java.threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/14 8:28
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class LockExample {
    private static final ReentrantLock FAIR_LOCK = new ReentrantLock(true);
    private static int i;

    public static void add() {
        FAIR_LOCK.lock();
        try {
            Thread.yield();
            i++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            FAIR_LOCK.unlock();
        }
    }

    public static void unsafeAdd() {
        i++;
    }
}
