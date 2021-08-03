package me.stone.training.platform.training.pattern.factory.method;

import me.stone.training.platform.training.pattern.factory.Product;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/2 22:47
 */
public class MethodApp {
    public static void main(String[] args) {
        final RedProductFactory redProductFactory = new RedProductFactory();
        final Product product = redProductFactory.create();
        System.out.println(product.description());

        final BlackProductFactory blackProductFactory = new BlackProductFactory();
        final Product otherProduct = blackProductFactory.create();
        System.out.println(otherProduct.description());
    }
}
