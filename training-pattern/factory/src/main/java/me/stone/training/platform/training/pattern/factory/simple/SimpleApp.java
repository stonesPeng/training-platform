package me.stone.training.platform.training.pattern.factory.simple;

import me.stone.training.platform.training.pattern.factory.Product;
import me.stone.training.platform.training.pattern.factory.RedProduct;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/2 22:40
 */
public class SimpleApp {
    public static void main(String[] args) {
        final ProductFactory productFactory = new ProductFactory();
        final Product product = productFactory.create(ProductFactory.RED);
        System.out.println(product.description());

        final ProductUltraFactory productUltraFactory = new ProductUltraFactory();
        final Product otherProduct = productUltraFactory.create(RedProduct.class);
        System.out.println(otherProduct.description());
    }
}
