package me.stone.training.platform.training.java.threads;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/29 22:07
 */
public class SyncExample {

    private static int i = 1;

    public synchronized static void increase1() {
        i++;
    }

    public synchronized void increase2() {
        i++;
    }

    public static void main(String[] args) {
        synchronized (SyncExample.class) {

        }
        SyncExample.increase1();
    }

    public void increase3() {
        synchronized (SyncExample.class) {
            i++;
        }
    }

}
