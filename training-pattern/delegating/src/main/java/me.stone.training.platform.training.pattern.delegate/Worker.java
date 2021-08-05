package me.stone.training.platform.training.pattern.delegate;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/5
 */
public interface Worker {

    /**
     * 干事的工种类型
     *
     * @return 工种类型
     */
    String workType();

    /**
     * 干事当然是干事情
     */
    void doingJob(String command);
}
