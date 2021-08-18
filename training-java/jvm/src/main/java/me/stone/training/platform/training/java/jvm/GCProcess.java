package me.stone.training.platform.training.java.jvm;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/26 19:50
 */
public class GCProcess {

    private static GCProcess gcBean;

    @SneakyThrows
    public static void main(String[] args) {
        gcBean = new GCProcess();
        gcBean = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        if (gcBean == null) {
            System.out.println("gcBean == null");
        } else {
            System.out.println("gcBean is available");
        }
        TimeUnit.SECONDS.sleep(1);
        gcBean = null;
        System.gc();
        if (gcBean == null) {
            System.out.println("gcBean == null");
        } else {
            System.out.println("gcBean is available");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("#finalize in...");
        gcBean = this;
    }
}
