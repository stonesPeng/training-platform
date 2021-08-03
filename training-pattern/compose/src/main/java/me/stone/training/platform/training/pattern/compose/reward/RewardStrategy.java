package me.stone.training.platform.training.pattern.compose.reward;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/30 17:53
 */
public abstract class RewardStrategy {

    abstract void rewardBy(String userTrait);

    void afterRewarded() {
        //执行奖励策略后统一要处理的事情
    }

    static class OldUserRewardStrategy extends RewardStrategy {

        @Override
        void rewardBy(String userTrait) {

        }
    }

}
