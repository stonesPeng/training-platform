package me.stone.training.platform.training.pattern.factory.simple;

import me.stone.training.platform.training.pattern.factory.BlackProduct;
import me.stone.training.platform.training.pattern.factory.Product;
import me.stone.training.platform.training.pattern.factory.RedProduct;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/2 22:27
 */
public class ProductFactory {

    static final String RED = "red";
    static final String BLACK = "black";

    public Product create(String trait) {
        if (trait.equals(RED)) {
            return new RedProduct();
        } else if (trait.equals(BLACK)) {
            return new BlackProduct();
        }
        throw new IllegalArgumentException();
    }
}
