package me.stone.training.platform.training.pattern.singleton;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/25
 */
public class SingletonInDoubleCheckLock {
    private volatile static SingletonInDoubleCheckLock INSTANCE = null;

    private SingletonInDoubleCheckLock() {
    }

    public static SingletonInDoubleCheckLock getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonInDoubleCheckLock.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonInDoubleCheckLock();
                }
            }
        }
        return INSTANCE;
    }
}
