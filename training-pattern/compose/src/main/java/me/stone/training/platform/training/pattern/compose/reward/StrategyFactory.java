package me.stone.training.platform.training.pattern.compose.reward;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/4 21:17
 */
public abstract class StrategyFactory<T> {
    abstract RewardStrategy createStrategy(Class<T> c);
}