package me.stone.training.platform.training.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/22 19:16
 */
public class VolatileExample {

    /**
     * thread safely
     */
    //public static volatile  boolean run = true;
    /**
     * thread not safely
     */
    public static volatile boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        final Thread t = new Thread(() -> {
            int i = 0;
            while (!run) {
                i++;
                Thread.yield();
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(10);
        run = true;
    }
}
