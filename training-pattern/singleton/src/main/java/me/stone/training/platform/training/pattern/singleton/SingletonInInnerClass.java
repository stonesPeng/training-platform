package me.stone.training.platform.training.pattern.singleton;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/26
 */
public class SingletonInInnerClass {
    private SingletonInInnerClass() {
    }

    public static SingletonInInnerClass getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final SingletonInInnerClass INSTANCE = new SingletonInInnerClass();
    }
}
