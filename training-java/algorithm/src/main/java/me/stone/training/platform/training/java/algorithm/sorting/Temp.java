package me.stone.training.platform.training.java.algorithm.sorting;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/30 15:31
 */
public interface Temp {


    @SneakyThrows
    static void sci1() {
        String path = "C://Users//honor//Desktop//eeldx013.p08";
        String file = "C://Users//honor//Desktop//eeldx013-result.p08";
        String line;
        final BufferedReader br = new BufferedReader(new FileReader(path));
        final BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        final DecimalFormat decimalFormat = new DecimalFormat("0.00000E0");
//        int n=1;
        while (null != (line = br.readLine())) {
//            if(n==2481){
//                break;
//            }
//            n = n+1;
            System.out.println(line);
            String[] arr = line.split("\\s+");
            if (arr[1].equals("-1")) {
                for (int i = 0; i < arr.length; i++) {
                    String str = arr[i];
                    if (i > 3) {
                        final BigDecimal multiply = BigDecimal.valueOf(Double.parseDouble(arr[i])).multiply(BigDecimal.valueOf(1.3));
                        str = decimalFormat.format(multiply);
                    }
                    bw.write(str);
                    if (i != arr.length - 1) {
                        if (i == 1) {
                            bw.write("  ");
                        } else {
                            bw.write(" ");
                        }
                    }
                }
                bw.write("\r\n");
            } else {
                for (int i = 0; i < arr.length; i++) {
                    String str = arr[i];
                    if (str.isEmpty() || str.trim().isEmpty()) {

                    } else {
                        final BigDecimal multiply = BigDecimal.valueOf(Double.parseDouble(arr[i])).multiply(BigDecimal.valueOf(1.3));
                        str = decimalFormat.format(multiply);
                    }
                    bw.write(str);
                    if (i != arr.length - 1) {
                        bw.write(" ");
                    }
                }
                bw.write("\r\n");
            }
        }
        br.close();
        bw.close();
    }

    @SneakyThrows
    static void sci2() {
        String path = "C://Users//honor//Desktop//pdeel13.p08";
        String file = "C://Users//honor//Desktop//pdeel13-result.p08";
        String line;
        final BufferedReader br = new BufferedReader(new FileReader(path));
        final BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        final DecimalFormat decimalFormat = new DecimalFormat("0.00000E0");
//        int n=1;
        while (null != (line = br.readLine())) {
//            if(n==42){
//                break;
//            }
//            n = n+1;
            System.out.println(line);
            String[] arr = line.split("\\s+");
            if (arr[1].equals("13")) {
                for (int i = 0; i < arr.length; i++) {
                    bw.write(arr[i]);
                    bw.write(" ");
                }
                bw.write("\r\n");
            } else {
                for (int i = 0; i < arr.length; i++) {
                    String str = arr[i];
                    if ((str.isEmpty() || str.trim().isEmpty()) || i == 1) {

                    } else {
                        final BigDecimal multiply = BigDecimal.valueOf(Double.parseDouble(arr[i])).multiply(BigDecimal.valueOf(1.3));
                        str = decimalFormat.format(multiply);
                    }
                    bw.write(str);
                    if (i != arr.length - 1) {
                        bw.write(" ");
                    }
                }
                bw.write("\r\n");
            }
        }
        br.close();
        bw.close();
    }


    @SneakyThrows
    static void sci3() {
        String path = "C://Users//honor//Desktop//data//stopping power.txt";
        String file = "C://Users//honor//Desktop//data//stopping power-result.txt";
        String line;
        final BufferedReader br = new BufferedReader(new FileReader(path));
        final BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        final DecimalFormat decimalFormat = new DecimalFormat("0.00000E00");
//        int n=1;
        while (null != (line = br.readLine())) {
//            if(n==42){
//                break;
//            }
//            n = n+1;
            System.out.println(line);
            String[] arr = line.split("\\s+");
            if (line.contains("**")) {
                bw.write(line);
                bw.write("\r\n");
            } else {
                for (int i = 0; i < arr.length; i++) {
                    String str = arr[i];
                    if ((str.isEmpty() || str.trim().isEmpty()) || i == 1) {

                    } else {
                        final BigDecimal multiply = BigDecimal.valueOf(Double.parseDouble(arr[i])).multiply(BigDecimal.valueOf(1.1));
                        str = decimalFormat.format(multiply);
                        if (!str.contains("-")) {
                            final String[] es = str.split("E");
                            str = es[0] + "E+" + es[1];
                        }
                    }
                    bw.write(str);
                    if (i != arr.length - 1) {
                        bw.write(" ");
                    }
                }
                bw.write("\r\n");
            }
        }
        br.close();
        bw.close();
    }


    static void main(String[] args) {
        Temp.sci1();
//        Temp.sci3();
    }


}
