package me.stone.training.platform.training.java.crypto.encryption.symmetric;

import lombok.SneakyThrows;
import me.stone.training.platform.training.java.string.converter.ArraysStringConverter;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 对称加密具体实现
 * @since 2021/6/9 23:16
 */
public interface SymmetricUtils {

    /**
     * 对称加密，含有偏移量（expose）
     *
     * @param inputData  待加密的文本
     * @param cipherMode 具体加密模式
     * @param secretKey  密钥，格式为HexString
     * @param iv         偏移量，格式为HexString
     * @return 加密后的数据
     */
    static byte[] encryptionWithIv(String inputData,
                                   @NotNull String cipherMode,
                                   @NotNull String secretKey,
                                   @NotNull String iv) {
        final byte[] bytes = inputData.getBytes(StandardCharsets.UTF_8);
        final String symmetricMode = CipherMode.parseSymmetricMode(cipherMode);
        final SecretKeySpec key = generateKey(ArraysStringConverter.HexStringToByteArray(secretKey), symmetricMode);
        return doEncryption(bytes, cipherMode, key, ArraysStringConverter.HexStringToByteArray(iv));
    }

    /**
     * 对称加密（expose）
     *
     * @param inputData  待加密的文本
     * @param cipherMode 具体加密模式
     * @param secretKey  密钥，格式为HexString
     * @return 加密后的数据
     */
    static byte[] encryption(String inputData,
                             @NotNull String cipherMode,
                             @NotNull String secretKey) {
        final byte[] bytes = inputData.getBytes(StandardCharsets.UTF_8);
        final String symmetricMode = CipherMode.parseSymmetricMode(cipherMode);
        final SecretKeySpec key = generateKey(ArraysStringConverter.HexStringToByteArray(secretKey), symmetricMode);
        return doEncryption(bytes, cipherMode, key, null);
    }


    @SneakyThrows
    static byte[] doEncryption(byte[] inputData,
                               @NotNull String cipherMode,
                               @NotNull SecretKey secretKey,
                               byte[] vector) {
        if (!CipherMode.contains(cipherMode) || inputData == null) {
            throw new IllegalArgumentException();
        }
        final Cipher cipher = Cipher.getInstance(cipherMode);
        if (CipherMode.hasVector(cipherMode)) {
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(vector);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        }
        return cipher.doFinal(inputData);
    }

    /**
     * 解密，含有偏移量（expose）
     *
     * @param inputData  待加密的文本  数据格式为HexString
     * @param cipherMode 具体加密模式
     * @param secretKey  密钥，格式为HexString
     * @return 解密后的数据
     */
    @SneakyThrows
    static String decryption(String inputData,
                             @NotNull String cipherMode,
                             @NotNull String secretKey) {
        final byte[] bytes = ArraysStringConverter.HexStringToByteArray(inputData);
        final String symmetricMode = CipherMode.parseSymmetricMode(cipherMode);
        final SecretKeySpec key = generateKey(ArraysStringConverter.HexStringToByteArray(secretKey), symmetricMode);
        return new String(doDecryption(bytes, cipherMode, key, null));
    }

    /**
     * 解密，含有偏移量（expose）
     *
     * @param inputData  待加密的文本  数据格式为HexString
     * @param cipherMode 具体加密模式
     * @param secretKey  密钥，格式为HexString
     * @param iv         偏移量，格式为HexString
     * @return 解密后的数据
     */
    @SneakyThrows
    static String decryptionWithIv(String inputData,
                                   @NotNull String cipherMode,
                                   @NotNull String secretKey,
                                   @NotNull String iv) {
        final byte[] bytes = ArraysStringConverter.HexStringToByteArray(inputData);
        final String symmetricMode = CipherMode.parseSymmetricMode(cipherMode);
        final SecretKeySpec key = generateKey(ArraysStringConverter.HexStringToByteArray(secretKey), symmetricMode);
        return new String(doDecryption(bytes, cipherMode, key, ArraysStringConverter.HexStringToByteArray(iv)));
    }

    @SneakyThrows
    static byte[] doDecryption(byte[] inputData,
                               @NotNull String cipherMode,
                               @NotNull SecretKey secretKey,
                               byte[] vector) {
        if (!CipherMode.contains(cipherMode)) {
            throw new IllegalArgumentException();
        }
        final Cipher cipher = Cipher.getInstance(cipherMode);
        if (CipherMode.hasVector(cipherMode)) {
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(vector);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
        }
        return cipher.doFinal(inputData);
    }

    static SecretKeySpec generateKey(byte[] password, String symmetricMode) {
        return new SecretKeySpec(password, symmetricMode);
    }


    /**
     * 密钥/偏移量生成器
     */
    interface SecureRandomGenerator {
        /**
         * 获取加密密钥
         *
         * @param symmetricMode     对称加密模式
         * @param lenOfSymmetricKey 密钥长度
         * @param password          指定的种子，用于生成密钥
         * @return 加密密钥
         */
        @SneakyThrows
        static SecretKey symmetricKey(@MagicConstant(valuesFromClass = SymmetricMode.class) String symmetricMode,
                                      int lenOfSymmetricKey,
                                      @Nullable String password) {
            final SecureRandom secureRandom = (password == null || password.trim().isEmpty()) ? new SecureRandom() : new SecureRandom(password.getBytes(StandardCharsets.UTF_8));
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(symmetricMode);
            keyGenerator.init(lenOfSymmetricKey, secureRandom);
            return keyGenerator.generateKey();
        }


        /**
         * 获取加密密钥
         *
         * @param symmetricMode     对称加密模式
         * @param lenOfSymmetricKey 密钥长度
         * @return 加密密钥
         */
        @SneakyThrows
        static SecretKey symmetricKey(@MagicConstant(valuesFromClass = SymmetricMode.class) String symmetricMode,
                                      int lenOfSymmetricKey) {
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(symmetricMode);
            keyGenerator.init(lenOfSymmetricKey);
            return keyGenerator.generateKey();
        }

        /**
         * 生成指定长度的随机数
         *
         * @param lenOfSymmetricKey 随机数的长度
         * @return 随机数
         */
        static byte[] symmetricKey(int lenOfSymmetricKey) {
            final SecureRandom secureRandom = new SecureRandom();
            final byte[] bytes = new byte[lenOfSymmetricKey];
            secureRandom.nextBytes(bytes);
            return bytes;
        }

        /**
         * 获取初始向量
         *
         * @return 初始向量
         */
        static byte[] initializationVector(int length) {
            final byte[] initializationVector = new byte[length];
            final SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(initializationVector);
            return initializationVector;
        }

    }

    /**
     * 加密的具体模式
     */
    interface CipherMode {

        String AES_ECB_PKCS5PADDING = "AES/ECB/PKCS5Padding";

        String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";

        String DES_CBC_PKCS5PADDING = "DES/CBC/PKCS5Padding";

        /**
         * CipherModes
         */
        List<String> CipherModes = Arrays.asList(
            AES_ECB_PKCS5PADDING,
            AES_CBC_PKCS5PADDING,
            DES_CBC_PKCS5PADDING
            //TODO add Modes
        );

        /**
         * CipherModesWithVector
         */
        List<String> CipherModesWithVector = Arrays.asList(
            AES_CBC_PKCS5PADDING,
            DES_CBC_PKCS5PADDING
        );

        static String parseSymmetricMode(String mode) {
            if (mode.contains(SymmetricMode.AES)) {
                return SymmetricMode.AES;
            } else if (mode.contains(SymmetricMode.DES)) {
                return SymmetricMode.DES;
            } else {
                throw new IllegalArgumentException("unknown mode");
            }
        }

        static boolean contains(String mode) {
            return CipherModes.contains(mode);
        }

        static boolean hasVector(String mode) {
            return CipherModesWithVector.contains(mode);
        }
    }

    /**
     * 对称加密类型
     */
    interface SymmetricMode {

        String AES = "AES";

        String DES = "DES";

        List<String> SymmetricModes = Arrays.asList(
            AES,
            DES
            //TODO add Modes
        );

        static boolean contains(String mode) {
            return SymmetricModes.contains(mode);
        }
    }

    static void main(String[] args) {
        test3();
        //test2();
    }

    static void test1() {
        String originData = "1231231231231";
        final SecretKey secretKey = SecureRandomGenerator.symmetricKey(SymmetricMode.AES, 128);
        System.out.println(ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()));
        final byte[] encryptionData = encryption(originData, CipherMode.AES_ECB_PKCS5PADDING, ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()));
        final String encryptionHexData = ArraysStringConverter.convertByteArrayToHexString(encryptionData);
        System.out.println(encryptionHexData);
        final String decryptionData = decryption(encryptionHexData, CipherMode.AES_ECB_PKCS5PADDING, ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()));
        System.out.println(decryptionData);
    }

    static void test2() {
        String originData = "1231231231231";
        final SecretKey secretKey = SecureRandomGenerator.symmetricKey(SymmetricMode.DES, 56);
        System.out.println(ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()));
        final byte[] iv = SecureRandomGenerator.initializationVector(8);
        System.out.println(ArraysStringConverter.convertByteArrayToHexString(iv));
        final byte[] encryptionData = encryptionWithIv(originData, CipherMode.DES_CBC_PKCS5PADDING, ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()), ArraysStringConverter.convertByteArrayToHexString(iv));
        final String encryptionHexData = ArraysStringConverter.convertByteArrayToHexString(encryptionData);
        System.out.println(encryptionHexData);
        final String decryptionData = decryptionWithIv(encryptionHexData, CipherMode.DES_CBC_PKCS5PADDING, ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()), ArraysStringConverter.convertByteArrayToHexString(iv));
        System.out.println(decryptionData);
    }

    static void test3() {
        String originData = "1231231231231";
        final SecretKeySpec secretKey = generateKey("RuOgacx137uGk8fB".getBytes(StandardCharsets.UTF_8), SymmetricMode.AES);
        System.out.println(ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()));
        final byte[] encryptionData = encryption(originData, CipherMode.AES_ECB_PKCS5PADDING, ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()));
        final String encryptionHexData = ArraysStringConverter.convertByteArrayToHexString(encryptionData);
        System.out.println(encryptionHexData);
        final String decryptionData = decryption(encryptionHexData, CipherMode.AES_ECB_PKCS5PADDING, ArraysStringConverter.convertByteArrayToHexString(secretKey.getEncoded()));
        System.out.println(decryptionData);

    }

}
