/*
 *     Copyright (c) 2021.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: Bits.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2021-08-06 23:33:08
 */

package me.stone.training.platform.training.common.tool.utils;

import lombok.val;
import lombok.var;
import org.jetbrains.annotations.Range;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import java.util.List;

public interface Bits {

    //region Old BitFlagUtil part
    /**
     * Util Int flag <==> int Bit map
     * 1 = [31^0,1] ; 2= [30^0,1,0]
     */


    static int BITS_LENGTH_INT = 32;

    /**
     * @param flag int num which represents some status , because java int has 31 bits to represents num
     * @return per bit in per byte , length is 31
     */
    static byte[] toBits(int flag) {
        byte[] bytes = new byte[BITS_LENGTH_INT];
        for (int i = BITS_LENGTH_INT - 1; i >= 0; i--) {
            int mask = 1 << i;
            bytes[i] = (flag & mask) != 0 ? (byte) 1 : 0;
        }
        return bytes;
    }

    /**
     * Judge whether 1 at index of the flag
     *
     * @param flag    flag
     * @param flagIdx 1-32,index of flag , int has 32 bits, so index must be less than 32.
     * @return 1 true 0 false
     */
    static boolean whetherEnable(int flag, int flagIdx) {
        if (flagIdx > BITS_LENGTH_INT)
            throw new UnsupportedOperationException("Flag index must be less than 32.");
        if (flagIdx < 1)
            throw new UnsupportedOperationException("Flag index must be larger than 1.");

        byte[] bytes = toBits(flag);
        return bytes[flagIdx - 1] == 1;
    }

    /**
     * set index of bits which converts from flag to 1 or 0.
     *
     * @param flag    flag
     * @param open    1 or 0
     * @param flagIdx 1-32,index of flag , int has 32 bits, so index must be less than 32.
     * @return flag which index is set to open value
     */
    static int setFlag(int flag, boolean open, int flagIdx) {
        if (flagIdx > BITS_LENGTH_INT)
            throw new UnsupportedOperationException("Flag index must be less than 32.");
        if (flagIdx < 1)
            throw new UnsupportedOperationException("Flag index must be larger than 1.");

        byte[] bytes = toBits(flag);
        bytes[flagIdx - 1] = open ? (byte) 1 : 0;

        return toInt(bytes);
    }

    /**
     * convert bytes to int.
     *
     * @param bytes they are bits map. eg. 1 = [31&0,1] , 2 = [30^0,1,0]
     * @return
     */
    static int toInt(byte[] bytes) {
        var restoredFlag = 0;
        for (int index = 0; index < bytes.length; index++) {
            restoredFlag += bytes[index] == 1 ? Math.pow(2, index) : 0;
        }

        return restoredFlag;
    }

    /**
     * batch version of sef flag
     *
     * @param flag      flag
     * @param opens     1 or 0  list
     * @param flagIdxes 1-32,index of flag list, int has 32 bits, so index must be less than 32.
     * @return flag which index is set to open value
     */
    static int setFlag(int flag, List<Boolean> opens, List<Integer> flagIdxes) {
        if (opens.size() != flagIdxes.size())
            throw new UnsupportedOperationException("Size of opens isn't equal to size of flag indexes");

        if (flagIdxes.stream().anyMatch(idx -> idx > BITS_LENGTH_INT))
            throw new UnsupportedOperationException("Flag index must be less than 32.");

        if (flagIdxes.stream().anyMatch(idx -> idx < 1))
            throw new UnsupportedOperationException("Flag index must be larger than 1.");

        byte[] bytes = toBits(flag);
        for (int index = 0; index < opens.size(); index++) {
            bytes[flagIdxes.get(index) - 1] = opens.get(index) ? (byte) 1 : 0;
        }

        return toInt(bytes);
    }

    /**
     * judge standard flag whether contains instance flag
     * eg. standard flag [1,1,1,1] , instance flag [0,0,0,1] ,result is true
     * standard flag [0,0,0,1] , instance flag [1,0,0,0] ,result is false
     * standard flag [1,1,0,0,] , instance flag [0,1,0,0] , result is true
     *
     * @param stdFlags  standard flags
     * @param instFlags instance flags
     * @return result
     */
    static boolean containsOf(byte[] stdFlags, byte[] instFlags) {
        if (stdFlags == null)
            throw new UnsupportedOperationException("Standard flags is null.");

        if (instFlags == null)
            throw new UnsupportedOperationException("Instance flags is null.");

        if (stdFlags.length != instFlags.length)
            throw new UnsupportedOperationException("Length of two flags isn't same");

        for (int index = 0; index < instFlags.length; index++) {
            if (instFlags[index] == 1 && stdFlags[index] == 0)
                return false;
        }

        return true;
    }

    /**
     * judge standard flag whether contains instance flag
     * eg. standard flag [1,1,1,1] , instance flag [0,0,0,1] ,result is true
     * standard flag [0,0,0,1] , instance flag [1,0,0,0] ,result is false
     * standard flag [1,1,0,0,] , instance flag [0,1,0,0] , result is true
     *
     * @param std  standard int flag
     * @param inst instance int flag
     * @return
     */
    static boolean containsOf(int std, int inst) {
        val stdFlags = toBits(std);
        val instFlags = toBits(inst);

        return containsOf(stdFlags, instFlags);
    }

    //endregion
    //region one bit operation
    static long setBit(long b, @Range(from = 0, to = 63) int pos) {
        return b | (1L << pos);
    }

    static long clearBit(long b, @Range(from = 0, to = 63) int pos) {
        return b & ~(1L << pos);
    }

    static boolean readBit(long b, @Range(from = 0, to = 63) int pos) {
        return (b & (1L << pos)) != 0;
    }

    static int setBit(int b, @Range(from = 0, to = 31) int pos) {
        return b | (1 << pos);
    }

    static int clearBit(int b, @Range(from = 0, to = 31) int pos) {
        return b & ~(1 << pos);
    }

    static boolean readBit(int b, @Range(from = 0, to = 31) int pos) {
        return (b & (1 << pos)) != 0;
    }

    static byte setBit(byte b, @Range(from = 0, to = 7) int pos) {
        return (byte) (b | (byte) (1 << pos));
    }

    static byte clearBit(byte b, @Range(from = 0, to = 7) int pos) {
        return (byte) (b & ~(byte) (1 << pos));
    }

    static boolean readBit(byte b, @Range(from = 0, to = 7) int pos) {
        return (b & (1 << pos)) != 0;
    }

    //endregion
    //region bit combine

    /**
     * 按位 组合两 int 为long
     *
     * @param v1 first int
     * @param v2 second int
     * @return long (maybe overflow)!!
     */
    static long join(int v1, int v2) {
        return (long) v1 << 32 | ((long) v2 & 0xffffffffL);
    }


    /**
     * 拆分聚合的数值
     *
     * @param joint the joined long
     * @return (first int, second int)
     * @see Bits#join(int, int, int) , equals third int is 32
     */
    static Tuple2<Integer, Integer> split(long joint) {
        return Tuple.tuple((int) (joint >> 32), ((int) joint));
    }

    /**
     * 按位 组合两 int 为long
     *
     * @param v1 first int
     * @param v2 second int
     * @param n  第二个值占据的位数量
     * @return long (maybe overflow)!!
     */
    static long join(int v1, int v2, @Range(from = 1, to = 63) int n) {
        return (long) v1 << n | ((long) v2 & ((1L << n) - 1));
    }

    /**
     * 拆分聚合的数值
     *
     * @param joint the joined long
     * @param n     第二个值占据的位数量
     * @return (int, int)
     */
    static Tuple2<Integer, Integer> split(long joint, @Range(from = 1, to = 63) int n) {
        return Tuple.tuple((int) (joint >> n), (int) (joint & ((1L << n) - 1)));
    }

    /**
     * 聚合 一个 long 和一个integer
     *
     * @param v1 first long
     * @param v2 second int
     * @param n  第二个值占据的位数量
     * @return long (maybe overflow)!!
     */
    static long joinLong(long v1, int v2, @Range(from = 1, to = 63) int n) {
        return v1 << n | ((long) v2 & ((1L << n) - 1));
    }

    /**
     * 拆分聚合的数值
     *
     * @param joint the joined long
     * @param n     第二个值占据的位数量
     * @return (first long, second int)
     */
    static Tuple2<Long, Integer> splitLong(long joint, @Range(from = 1, to = 63) int n) {
        return Tuple.tuple((joint >> n), (int) (joint & ((1L << n) - 1)));
    }

    //endregion

}
