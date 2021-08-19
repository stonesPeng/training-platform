package me.stone.training.platform.training.java.threads;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/29 19:19
 */
public class DoubleCheckingLock {

    private static volatile Instance instance;

    public static Instance getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckingLock.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }

    ;

    static class Instance {
    }
}
