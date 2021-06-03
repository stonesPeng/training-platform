package me.stone.training.platform.training.java.crypto.digest;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;


/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 摘要算法
 * @since 2021/5/19 9:01
 */
public interface DigestUtils {

    String SHA1 = "SHA-1";
    String MD5 = "MD5";
    String SHA256 = "SHA-256";
    String SHA512 = "SHA-512";

    static String SHA256(byte[] bytes) {
        return digest(bytes, SHA256);
    }

    static String SHA512(byte[] bytes) {
        return digest(bytes, SHA512);
    }

    static String MD5(byte[] bytes) {
        return digest(bytes, MD5);
    }

    static String SHA1(byte[] bytes) {
        return digest(bytes, SHA1);
    }

    @SneakyThrows
    static String digest(byte[] aByte, @NotNull String algorithm) {
        final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        final byte[] digest = messageDigest.digest(aByte);
        final StringBuilder sb = new StringBuilder();
        //对密文进行迭代，将其转换为16进制
        for (byte b : digest) {
            String toHexString = Integer.toHexString(b & 0xff);
            if (toHexString.length() == 1) {
                toHexString = "0" + toHexString;
            }
            sb.append(toHexString);
        }
        return sb.toString();
    }
}