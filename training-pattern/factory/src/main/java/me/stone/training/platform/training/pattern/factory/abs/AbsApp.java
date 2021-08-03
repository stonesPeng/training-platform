package me.stone.training.platform.training.pattern.factory.abs;

import me.stone.training.platform.training.pattern.factory.Product;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/2 20:06
 */
public class AbsApp {
    public static void main(String[] args) {
        final RedComposeFactory redComposeFactory = new RedComposeFactory();
        final AnotherProduct anotherProduct = redComposeFactory.createAnotherProduct();
        final Product product = redComposeFactory.createProduct();
        System.out.println(anotherProduct.description());
        System.out.println(product.description());
    }
}
