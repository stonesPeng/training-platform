package me.stone.training.platform.training.pattern.singleton;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/27
 */
public class SingletonInThreadLocal {
    private static final ThreadLocal<SingletonInThreadLocal> threadLocalInstance =
        new ThreadLocal<SingletonInThreadLocal>() {
            @Override
            protected SingletonInThreadLocal initialValue() {
                return new SingletonInThreadLocal();
            }
        };

    private SingletonInThreadLocal() {

    }

    public static SingletonInThreadLocal getInstance() {
        return threadLocalInstance.get();
    }

    public static void main(String[] args) {
        System.out.println(SingletonInThreadLocal.getInstance());
        System.out.println(SingletonInThreadLocal.getInstance());
        new Thread(() -> System.out.println(SingletonInThreadLocal.getInstance())).start();
        new Thread(() -> System.out.println(SingletonInThreadLocal.getInstance())).start();
    }

}
