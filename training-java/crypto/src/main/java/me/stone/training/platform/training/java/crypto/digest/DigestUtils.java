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