package me.stone.training.platform.training.pattern.singleton;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/24
 */
public class SingletonInHungry {
    private static final SingletonInHungry INSTANCE = new SingletonInHungry();

    private SingletonInHungry() {
    }

    public static SingletonInHungry getInstance() {
        return INSTANCE;
    }
}
