package me.stone.training.platform.training.pattern.delegate;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/5
 */
public class WoodWorker implements Worker {
    /**
     * 干事的工种类型
     *
     * @return 工种类型
     */
    @Override
    public String workType() {
        return "WoodWorker";
    }

    /**
     * 干事当然是干事情
     */
    @Override
    public void doingJob(String command) {
        System.out.println("i am WoodWorker,receive command【 " + command + "】,start to work");
    }
}
