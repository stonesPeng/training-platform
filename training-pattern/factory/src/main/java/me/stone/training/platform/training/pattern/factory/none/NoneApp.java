package me.stone.training.platform.training.pattern.factory.none;

import me.stone.training.platform.training.pattern.factory.Product;
import me.stone.training.platform.training.pattern.factory.RedProduct;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/2 22:26
 */
public class NoneApp {
    public static void main(String[] args) {
        Product product = new RedProduct();
        System.out.println(product.description());
    }
}
