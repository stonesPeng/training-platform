package me.stone.training.platform.training.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/19 14:27
 */
public class CustomizeThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " of Thread running...");
    }

    public static void main(String[] args) {
        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exec " + Thread.currentThread().getName());
        });
        thread.start();
        thread.run();
    }

    public void test() {
        new Thread(() -> {

        }).start();

    }
}
