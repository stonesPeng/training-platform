package me.stone.training.platform.training.pattern.compose.reward;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/4 21:23
 */
public class RewardContext {
    final RewardStrategy rewardStrategy;

    public RewardContext(RewardStrategy rewardStrategy) {
        this.rewardStrategy = rewardStrategy;
    }

    public void doStrategy(String userTrait) {
        final int rewardAmount = rewardStrategy.rewardBy(userTrait);
        rewardStrategy.afterReward(userTrait, rewardAmount);
    }
}
