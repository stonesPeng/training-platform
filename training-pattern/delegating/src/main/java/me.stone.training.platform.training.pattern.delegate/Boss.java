package me.stone.training.platform.training.pattern.delegate;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/5
 */
public class Boss {
    public void command(String command, Manager manager) {
        manager.doing(command);
    }
}
