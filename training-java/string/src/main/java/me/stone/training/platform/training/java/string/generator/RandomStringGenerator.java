package me.stone.training.platform.training.java.string.generator;

import org.intellij.lang.annotations.MagicConstant;

import java.util.Random;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 随机生成字符串
 * @since 2021/6/8 7:56
 */
public interface RandomStringGenerator {

    String STR_ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 随机生成字符串
     *
     * @param length 字符串大小
     * @return 新生成的字符串
     */
    static String randomString(@MagicConstant(valuesFromClass = Long.class) int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must more than 0");
        }
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomInt = random.nextInt(STR_ALLOWED_CHARACTERS.length());
            randomString.append(STR_ALLOWED_CHARACTERS.charAt(randomInt));
        }
        return randomString.toString();
    }
}
