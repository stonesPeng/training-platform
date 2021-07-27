package me.stone.training.platform.training.java.jvm.cpu;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/7/26 20:26
 */
public class CpuDryOutExample {

    public static void dryOut() {
        while (true) {
            System.out.println("dry out utilization rate");
            //do something
        }
    }

    public static void main(String[] args) {
        CpuDryOutExample.dryOut();
    }
}
