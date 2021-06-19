package me.stone.training.platform.training.java.thread;

import java.util.concurrent.Callable;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/19 14:31
 */
public class CustomizeCallable implements Callable<String> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        final String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " of Callable running...");
        return threadName;
    }

}
