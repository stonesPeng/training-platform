package me.stone.training.platform.training.pattern.singleton;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/25
 */
public class SingletonInLazy {

    public static SingletonInLazy INSTANCE = null;

    static final Runnable RUNNABLE = () -> {
        final SingletonInLazy instance = SingletonInLazy.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);
    };

    private SingletonInLazy() {
    }

    public final static SingletonInLazy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonInLazy();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        final Thread thread1 = new Thread(RUNNABLE);
        final Thread thread2 = new Thread(RUNNABLE);
        thread1.start();
        thread2.start();
        System.out.println("end");

    }
}
