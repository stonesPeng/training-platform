package me.stone.training.platform.training.pattern.compose.reward;


import lombok.SneakyThrows;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/4 21:17
 */
public class FactorRewardStrategyFactory extends StrategyFactory {
    @SneakyThrows
    @Override
    RewardStrategy createStrategy(Class clazz) {
        return (RewardStrategy) clazz.newInstance();
    }
}