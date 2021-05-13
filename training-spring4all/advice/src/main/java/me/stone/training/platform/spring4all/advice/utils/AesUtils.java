package me.stone.training.platform.spring4all.advice.utils;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author honorStone
 */
public interface AesUtils {

    /**
     * 加密
     *
     * @param content    原文
     * @param encryptKey 密钥
     * @return 密文
     */
    @SneakyThrows
    static String encrypt(String content, String encryptKey) {
        final KeyGenerator gen = KeyGenerator.getInstance(AesConstant.AES_KEY);
        gen.init(128);
        final Cipher cipher = Cipher.getInstance(AesConstant.ALGORITHM_STR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), AesConstant.AES_KEY));
        final byte[] b = cipher.doFinal(content.getBytes(AesConstant.CHARSET));
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * 解密
     *
     * @param encryptStr 密文
     * @param decryptKey 密钥
     * @return 原文
     */
    @SneakyThrows
    static String decrypt(String encryptStr, String decryptKey) {
        final KeyGenerator gen = KeyGenerator.getInstance(AesConstant.AES_KEY);
        gen.init(128);
        final Cipher cipher = Cipher.getInstance(AesConstant.ALGORITHM_STR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), AesConstant.AES_KEY));
        final byte[] decryptBytes = cipher.doFinal(Base64.getDecoder().decode(encryptStr));
        return new String(decryptBytes);
    }

    @SneakyThrows
    static void main(String[] args) {
        final String encryptMessage = AesUtils.encrypt("123", "a8cdae675d2ed7200c3554b3139746b8");
        System.out.println(encryptMessage);
        final String decryptMessage = AesUtils.decrypt(encryptMessage, "a8cdae675d2ed7200c3554b3139746b8");
        System.out.println(decryptMessage);
    }

    /**
     * AES加密常量
     */
    interface AesConstant {
        /**
         * aes加密方式
         */
        String ALGORITHM_STR = "AES/ECB/PKCS5Padding";

        /**
         * 加密类型
         */
        String AES_KEY = "AES";

        /**
         * 编码格式
         */
        String CHARSET = "utf-8";
    }
}
