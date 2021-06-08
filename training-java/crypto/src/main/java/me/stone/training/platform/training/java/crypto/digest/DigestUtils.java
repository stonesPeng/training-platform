package me.stone.training.platform.training.java.crypto.digest;

import me.stone.training.platform.training.java.string.converter.ArraysStringConverter;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 摘要算法
 * @since 2021/5/19 9:01
 */
public interface DigestUtils {

    static String SHA256(byte[] bytes) throws NoSuchAlgorithmException {
        return digest(bytes, AlgorithmType.SHA256);
    }

    static String SHA512(byte[] bytes) throws NoSuchAlgorithmException {
        return digest(bytes, AlgorithmType.SHA512);
    }

    static String MD5(byte[] bytes) throws NoSuchAlgorithmException {
        return digest(bytes, AlgorithmType.MD5);
    }

    static String SHA1(byte[] bytes) throws NoSuchAlgorithmException {
        return digest(bytes, AlgorithmType.SHA1);
    }

    /**
     * 将待加密的内容和算法名称作为输入，并返回计算出的哈希值的十六进制形式
     *
     * @param aByte     待加密的内容
     * @param algorithm 算法名称
     * @return 哈希值的十六进制形式
     * @throws NoSuchAlgorithmException 没有找到加密算法异常
     */
    static String digest(byte[] aByte, @NotNull String algorithm) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        final byte[] digest = messageDigest.digest(aByte);
        return ArraysStringConverter.convertByteArrayToHexString(digest);
    }

    interface AlgorithmType {
        String SHA1 = "SHA-1";
        String MD5 = "MD5";
        String SHA256 = "SHA-256";
        String SHA512 = "SHA-512";
    }
}