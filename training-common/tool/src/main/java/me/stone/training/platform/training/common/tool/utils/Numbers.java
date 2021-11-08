/*
 *     Copyright (c) 2021.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: Numbers.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2021-08-06 23:32:59
 */

package me.stone.training.platform.training.common.tool.utils;

import lombok.val;
import lombok.var;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public interface Numbers {
    static long nullOrDefault(Long val, long defaultVal) {
        return val == null ? defaultVal : val;
    }

    static int nullOrDefault(Integer val, int defaultVal) {
        return val == null ? defaultVal : val;
    }

    static long nullOrZero(Long val) {
        return nullOrDefault(val, 0);
    }

    static long nullOrNegativeOne(Long val) {
        return nullOrDefault(val, -1);
    }

    static Long negativeOneThenNull(long val) {
        return val == -1 ? null : val;
    }

    static Long zeroThenNull(long val) {
        return val == 0L ? null : val;
    }

    static <T extends Number> T nullOrDefault(T val, T defaultVal) {
        return val == null ? defaultVal : val;
    }

    static String leftPad(char placeHolder, Number num, int len, boolean ignoreDecimal, boolean includePoint) {
        val s = num.toString();
        val decimal = s.indexOf('.');
        val leftLen = decimal != -1 ?
            ignoreDecimal ? (includePoint ? decimal + 1 : decimal) : (includePoint ? s.length() : s.length() - 1)
            : s.length();
        if (leftLen < len) {
            return com.medtreehealth.infra.common.api.util.Strings.repeat(placeHolder, len - leftLen) + num.toString();
        }
        return num.toString();
    }

    static String rightPad(char placeHolder, Number num, int len, boolean ignoreInteger, boolean includePoint) {
        val s = num.toString();
        val decimal = s.indexOf('.');
        val length = s.length();
        val rightLen = decimal != -1 ?
            ignoreInteger ? (!includePoint ? (length - decimal - 1) : (length - decimal - 2)) : (includePoint ? length : length - 1)
            : s.length();
        //System.out.println(rightLen);
        if (rightLen < len) {
            return num.toString() + com.medtreehealth.infra.common.api.util.Strings.repeat(placeHolder, len - rightLen);
        }
        return num.toString();
    }


    static int MAX_LONG_LEN = Long.toString(Long.MAX_VALUE).length();

    /**
     * 将Decimal处理为(值,缩放系数)
     *
     * @param v 待处理的值
     * @return (scale, value)
     * @throws IllegalStateException 当全部值溢出long容量
     * @see Numbers#restore(long, long)
     * @see Numbers#restore(Tuple2)
     */
    static Tuple2<Long, Long> scale(BigDecimal v) throws IllegalStateException {
        val chars = v.toPlainString();
        var is = 0;//integer scale
        var ds = 0;//decimal Scale
        val val = new StringBuilder(); // the value
        val pIdx = chars.split(Pattern.quote("."));
        { //process index part
            val integer = pIdx[0];
            for (int i = integer.length() - 1; i >= 0; i--) {
                if (integer.charAt(i) != '0') {
                    is = integer.length() - 1 - i;
                    break;
                }
            }
            val.append(integer).setLength(integer.length() - is);
        }
        //process decimal part
        if (pIdx.length > 1) {
            val decimal = pIdx[1];
            for (int i = decimal.length() - 1; i >= 0; i--) {
                if (decimal.charAt(i) != '0') {
                    ds = i + 1;
                    break;
                }
            }
            val.append(decimal.substring(0, ds));
        }
        if (val.length() > MAX_LONG_LEN) throw new IllegalStateException("BigDecimal Scale overflow!");
        return Tuple.tuple(((long) is << 32) | ((long) ds & 0xffffffffL), Long.parseLong(val.toString()));
    }

    /**
     * 还原缩放的Decimal
     *
     * @param value 值
     * @param scale scale
     * @return 还原的BigDecimal
     * @see Numbers#scale(BigDecimal)
     */
    static BigDecimal restore(long scale, long value) {
        val is = (int) (scale >> 32);
        val ds = ((int) scale);
        System.out.println(is + ":" + ds);
        val str = Long.toString(value);
        val len = str.length();
        val builder = new StringBuilder(str);
        if (ds > 0) {
            builder.insert(len - ds, '.');
        }
        if (is > 0) {
            builder.insert(len - ds, com.medtreehealth.infra.common.api.util.Strings.repeat('0', is));
        }
        return new BigDecimal(builder.toString());
    }

    static BigDecimal restore(Tuple2<Long, Long> pair) {
        return restore(pair.v1, pair.v2);
    }
/*
    public static void main(String[] args) {
        System.out.println(MAX_LONG_LEN);
        final Consumer<BigDecimal> test = b -> {
            val s = scale(b);
            val r = restore(s);
            val x = r.compareTo(b);
            System.out.println(b.toPlainString() + "=>" + r.toPlainString() + ":" + x + ":" + s);
            if (x != 0) {
                throw new RuntimeException("found error:" + b + ", " + r);
            }
        };
        val rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            test.accept(BigDecimal.valueOf(rnd.nextDouble() + rnd.nextInt(10000)));
        }
    }*/

}
