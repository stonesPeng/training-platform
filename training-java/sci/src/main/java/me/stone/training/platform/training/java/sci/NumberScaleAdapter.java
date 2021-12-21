package me.stone.training.platform.training.java.sci;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author honorStone
 * @description default description
 * @date 2021/11/13 12:04
 */
public interface NumberScaleAdapter {



    static void main(String[] args) throws IOException {
//
//        try {
//            String fileName = "Ag5-mc.txt";
//            String fileNameOutput = "Ag5-mc-output.txt";
//            Stream<String> lines = Files.lines(Paths.get("F:\\myself\\github\\inner\\training-platform\\training-java\\sci\\src\\main\\java\\me\\stone\\training\\platform\\training\\java\\sci\\" +fileName));
//            List<String> collect = lines.map(x -> {
//                String[] split = x.split("\\s+");
//                double v = Double.parseDouble(split[0]);
//                BigDecimal temp = BigDecimal.valueOf(v).setScale(4, RoundingMode.HALF_UP);
//                StringBuilder stringBuilder = new StringBuilder();
//                return stringBuilder.append(temp)
//                    .append("   ")
//                    .append(split[1])
//                    .toString();
//            }).collect(Collectors.toList());
//
//            FileWriter writer = new FileWriter("F:\\myself\\github\\inner\\training-platform\\training-java\\sci\\src\\main\\java\\me\\stone\\training\\platform\\training\\java\\sci\\"+fileNameOutput);
//            for(String str: collect) {
//                writer.write(str + System.lineSeparator());
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        traversal("D:\\onedrive\\OneDrive - Lenovo\\桌面\\out\\");
    }

    static void traversal(String path){
        final File file = new File(path);
        if(file.isDirectory()){
            Arrays.stream(file.listFiles())
                .filter(f->f.isFile())
                .forEach(f->{
                    final String name = f.getName();
                    String outName = "out-"+name;
                    try {
                        test(f.getName(),outName);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        }

    }



    static void test(String fileName,String fileNameOutput) throws IOException {
//        String fileName = "spc-enddet-01-改精度.dat";
//        String fileNameOutput = "spc-enddet-01-改精度-out.dat";
        Stream<String> lines = Files.lines(Paths.get("D:\\onedrive\\OneDrive - Lenovo\\桌面\\out\\" + fileName));
        final DecimalFormat decimalFormat = new DecimalFormat("0.000000E00");
//
//        stringBuilder.append(
//            " #  Results from PENMAIN. Output from energy-deposition detector #  1\n" +
//            " #  Deposited energy spectrum.\n" +
//            " #  WARNING: May be strongly biased if interaction forcing is used!\n" +
//            " #  1st column: deposited energy (eV).\n" +
//            " #  2nd column: probability density (1/(eV*particle)).\n" +
//            " #  3rd column: statistical uncertainty (3 sigma).");
        List<String> collect = lines.filter(x->!x.contains("#") && !x.isEmpty())
            .map(x -> {
                String[] split = x.split("\\s+");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("   ");
                for (String s : split) {
                    if(null == s || s.isEmpty()){
                        continue;
                    }
                    double v = Double.parseDouble(s);
                    BigDecimal temp = BigDecimal.valueOf(v);
                    String out = decimalFormat.format(temp);
                    if (!out.contains("-")) {
                        final String[] es = out.split("E");
                        out = es[0] + "E+" + es[1];
                    }
                    stringBuilder.append(out)
                        .append("  ")
                        .toString();
                }
                return stringBuilder.toString();
        }).collect(Collectors.toList());

        FileWriter writer = new FileWriter("D:\\onedrive\\OneDrive - Lenovo\\桌面\\out\\" + fileNameOutput);
        for (String str : collect) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
}
