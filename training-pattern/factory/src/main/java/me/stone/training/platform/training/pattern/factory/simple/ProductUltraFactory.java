package me.stone.training.platform.training.pattern.factory.simple;

import lombok.SneakyThrows;
import me.stone.training.platform.training.pattern.factory.Product;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/2 22:36
 */
public class ProductUltraFactory {

    @SneakyThrows
    public Product create(Class<? extends Product> clazz) {
        if (clazz != null) {
            return clazz.newInstance();
        }
        throw new IllegalArgumentException();
    }

}
