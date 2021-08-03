package me.stone.training.platform.training.pattern.strategy;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/3 16:10
 */
public class StrategyApp {
    public static void main(String[] args) {
        final Context context = new Context(new Strategy.DefaultStrategy());
        context.doStrategy();
    }
}
