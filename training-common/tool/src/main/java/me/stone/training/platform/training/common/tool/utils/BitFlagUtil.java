package me.stone.training.platform.training.common.tool.utils;

import lombok.val;
import lombok.var;

import java.util.Arrays;
import java.util.List;

/**
 * Util Int flag <==> int Bit map
 * 1 = [31^0,1] ; 2= [30^0,1,0]
 *
 * @see Bits
 * @deprecated use Bits instead
 */
@Deprecated
public interface BitFlagUtil {

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

    static void main(String[] args) {
        var inst = 1;
        var std = 1;

        var instFlags = toBits(inst);
        var stdFlags = toBits(std);

        var res = containsOf(stdFlags, instFlags);

        System.out.println(res);

        inst = setFlag(inst, true, 1);
        System.out.println(inst);

        instFlags = toBits(Integer.MAX_VALUE);
        stdFlags = toBits(Integer.MAX_VALUE);

        res = containsOf(stdFlags, instFlags);
        System.out.println(res);

        std = Integer.MAX_VALUE;
        inst = Integer.MAX_VALUE;
        stdFlags = toBits(std);
        inst = setFlag(inst, false, BITS_LENGTH_INT - 1);
        instFlags = toBits(inst);
        res = containsOf(stdFlags, instFlags);
        System.out.printf("std : %s , inst : %s , res: %s", std, inst, res);
        System.out.println();

        std = Integer.MIN_VALUE;
        stdFlags = toBits(std);
        inst = Integer.MIN_VALUE;
        instFlags = toBits(inst);
        res = containsOf(stdFlags, instFlags);
        System.out.printf("std : %s , inst : %s , res: %s", std, inst, res);
        System.out.println();


        std = Integer.MAX_VALUE;
        inst = Integer.MAX_VALUE;
        instFlags = toBits(inst);
        inst = setFlag(inst, Arrays.asList(false, false, false, false, false), Arrays.asList(1, 2, 3, 4, 5));
        instFlags = toBits(inst);
        res = containsOf(stdFlags, instFlags);
        System.out.printf("std : %s , inst : %s , res: %s", toInt(stdFlags), toInt(instFlags), res);
        System.out.println();

        std = Integer.MIN_VALUE;
        stdFlags = toBits(std);
        System.out.println(whetherEnable(std, 31));

    }


}
