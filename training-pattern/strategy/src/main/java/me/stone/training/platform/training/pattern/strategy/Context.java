package me.stone.training.platform.training.pattern.strategy;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/30 17:15
 */
public class Context {

    Strategy strategy = null;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doStrategy() {
        strategy.strategyImplementation();
    }

}
