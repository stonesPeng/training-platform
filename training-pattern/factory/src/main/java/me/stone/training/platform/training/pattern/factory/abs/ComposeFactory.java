package me.stone.training.platform.training.pattern.factory.abs;

import me.stone.training.platform.training.pattern.factory.Product;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/2 19:37
 */
public interface ComposeFactory {

    AnotherProduct createAnotherProduct();

    Product createProduct();

}
