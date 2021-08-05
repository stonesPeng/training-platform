package me.stone.training.platform.training.pattern.compose.reward;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/4 21:16
 */
public class OldUserRewardStrategy extends AbstractRewardStrategy {
    @Override
    public int rewardBy(String userTrait) {
        //老用户还想有奖励？
        return 0;
    }
}
