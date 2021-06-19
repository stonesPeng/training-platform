package me.stone.training.platform.training.java.thread;

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
}
