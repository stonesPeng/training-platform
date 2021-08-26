package me.stone.training.platform.training.pattern.singleton;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 对抗反射的单懒汉式单例设计模式
 * @since 2021/8/26
 */
public class UpgradeSingletonInLazy {

    public static UpgradeSingletonInLazy INSTANCE = null;
    static final Runnable RUNNABLE = () -> {
        final UpgradeSingletonInLazy instance = UpgradeSingletonInLazy.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);
    };

    private UpgradeSingletonInLazy() {
        if (INSTANCE != null) {
            throw new AssertionError();
        }
        System.out.println("----------私有构造方法实例化完成---------");
        INSTANCE = this;
    }

    public static UpgradeSingletonInLazy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UpgradeSingletonInLazy();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        UpgradeSingletonInLazy.getInstance();
        UpgradeSingletonInLazy.getInstance();
    }
}
