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
        final Context context = new Context(new DefaultStrategy());
        context.doStrategy();
        final Context anotherContext = new Context(new AnotherStrategy());
        anotherContext.doStrategy();
    }

    static void doStrategy(String strategyPoint) {
        Context context;
        if (strategyPoint.equals("default")) {
            context = new Context(new DefaultStrategy());
        } else if (strategyPoint.equals("another")) {
            context = new Context(new AnotherStrategy());
        } else {
            throw new IllegalArgumentException("Unknown strategy");
        }
        context.doStrategy();
    }
}
