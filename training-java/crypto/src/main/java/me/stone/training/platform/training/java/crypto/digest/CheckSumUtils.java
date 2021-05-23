package me.stone.training.platform.training.java.crypto.digest;

import java.security.MessageDigest;


/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc hash摘要的实践，用于项目对接过程中，重要参数完整性校验
 * @since 2021/5/19 9:01
 */
public interface CheckSumUtils {

    char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 计算并获取CheckSum
     *
     * @param appSecret appSecret  随机数（最大长度128个字符）
     * @param nonce     nonce  当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
     * @param curTime   curTime  SHA1(AppSecret + Nonce + CurTime),三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)
     * @return CheckSum
     */
    static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    /**
     * 计算并获取md5值
     *
     * @param requestBody 请求参数
     * @return Md5 hash
     */
    static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    static void main(String[] args) {
        System.out.println(CheckSumUtils.getCheckSum("-----", "sdfasdasdasda", "1621397096"));
    }
}