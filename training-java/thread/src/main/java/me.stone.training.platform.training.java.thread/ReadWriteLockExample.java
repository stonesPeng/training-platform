package me.stone.training.platform.training.java.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/18 16:56
 */
public class ReadWriteLockExample {

    static final Map<String, String> CACHE = new HashMap<>();
    static final ReentrantReadWriteLock RRW_LOCK = new ReentrantReadWriteLock();
    static final Lock R_LOCK = RRW_LOCK.readLock();
    static final Lock W_LOCK = RRW_LOCK.writeLock();

    public static String get(String key) {
        R_LOCK.lock();
        try {
            return CACHE.get(key);
        } finally {
            R_LOCK.unlock();
        }
    }

    public static void put(String key, String value) {
        W_LOCK.lock();
        try {
            CACHE.put(key, value);
        } finally {
            W_LOCK.unlock();
        }
    }
}
