package me.stone.training.platform.training.pattern.compose.reward;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/4 21:17
 */
public class NewUserRewardStrategy extends AbstractRewardStrategy {
    @Override
    public int rewardBy(String userTrait) {
        return 10;
    }
}
