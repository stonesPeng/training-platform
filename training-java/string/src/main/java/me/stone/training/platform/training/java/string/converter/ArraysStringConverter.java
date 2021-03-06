package me.stone.training.platform.training.java.string.converter;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc 数组转成字符串的转换器实现
 * @since 2021/6/8 16:09
 */
public interface ArraysStringConverter {
    /**
     * 转换字节数组到16进制的字符串
     *
     * @param arrayBytes 字节数组
     * @return 16进制的字符串
     */
    static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            stringBuffer.append(Integer.toString((arrayByte & 0xff) + 0x100, 16)
                .substring(1));
        }
        return stringBuffer.toString();
    }

    static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
        final int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        final byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
