package me.stone.training.platform.training.pattern.singleton;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/24
 */
public class SingletonInAnotherHungry {

    private static final SingletonInAnotherHungry INSTANCE;

    static {
        INSTANCE = new SingletonInAnotherHungry();
    }

    private SingletonInAnotherHungry() {
    }

    public static SingletonInAnotherHungry getInstance() {
        return INSTANCE;
    }
}
