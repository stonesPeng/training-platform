package me.stone.training.platform.training.pattern.compose.reward;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/30 17:53
 */
public abstract class AbstractRewardStrategy implements RewardStrategy {
    @Override
    public abstract int rewardBy(String userTrait);

    @Override
    public void afterReward(String userTrait, int rewardAmount) {
        //执行奖励策略后统一要处理的事情
        System.out.println("do something after reward");
    }
}
