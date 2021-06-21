package me.stone.training.platform.training.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/20 16:04
 */
public interface ThreadState {

    Object LOCK = new Object();

    Runnable TIME_WAITING = () -> {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable WAITING = () -> {
        while (true) {
            synchronized (ThreadState.class) {
                try {
                    Thread.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    Runnable BLOCKED = () -> {
        synchronized (LOCK) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(BLOCKED);
        executor.submit(BLOCKED);
        executor.shutdown();
    }

}
