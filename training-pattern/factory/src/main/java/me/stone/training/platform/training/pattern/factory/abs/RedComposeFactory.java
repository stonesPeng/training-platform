package me.stone.training.platform.training.pattern.factory.abs;

import me.stone.training.platform.training.pattern.factory.Product;
import me.stone.training.platform.training.pattern.factory.RedProduct;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/2 20:03
 */
public class RedComposeFactory implements ComposeFactory {
    @Override
    public AnotherProduct createAnotherProduct() {
        return new RedAnotherProduct();
    }

    @Override
    public Product createProduct() {
        return new RedProduct();
    }
}
