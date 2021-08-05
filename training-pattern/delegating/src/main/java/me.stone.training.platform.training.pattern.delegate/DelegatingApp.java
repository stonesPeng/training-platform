package me.stone.training.platform.training.pattern.delegate;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/5
 */
public class DelegatingApp {
    public static void main(String[] args) {
        new Boss().command("anybody Wood", new Manager());
        new Boss().command("anybody Elect", new Manager());
    }
}
