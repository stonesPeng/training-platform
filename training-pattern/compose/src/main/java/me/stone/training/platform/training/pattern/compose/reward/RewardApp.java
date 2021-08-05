package me.stone.training.platform.training.pattern.compose.reward;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/4 21:25
 */
public class RewardApp {

    static final String NEW_USER_TRAIT = "traitOfNewUser";
    static final String OLD_USER_TRAIT = "traitOfOldUser";

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("args error");
        }
        final String userTrait = args[0];
        final FactorRewardStrategyFactory factorRewardStrategyFactory = new FactorRewardStrategyFactory();
        RewardStrategy strategy;
        if (userTrait.equals(NEW_USER_TRAIT)) {
            strategy = factorRewardStrategyFactory.createStrategy(NewUserRewardStrategy.class);
        } else if (userTrait.equals(OLD_USER_TRAIT)) {
            strategy = factorRewardStrategyFactory.createStrategy(OldUserRewardStrategy.class);
        } else {
            throw new IllegalArgumentException("Unknown userTrait");
        }
        final RewardContext rewardContext = new RewardContext(strategy);
        rewardContext.doStrategy(userTrait);
    }
}
