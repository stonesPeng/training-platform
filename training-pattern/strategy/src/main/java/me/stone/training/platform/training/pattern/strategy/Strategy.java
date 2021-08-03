package me.stone.training.platform.training.pattern.strategy;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/30 17:15
 */
public interface Strategy {
    /**
     * strategy details
     */
    void strategyImplementation();

    class DefaultStrategy implements Strategy {

        /**
         * strategy details
         */
        @Override
        public void strategyImplementation() {
            System.out.println("default strategy");
        }
    }

    class AnotherStrategy implements Strategy {

        /**
         * strategy details
         */
        @Override
        public void strategyImplementation() {
            System.out.println("another strategy");
        }
    }
}
