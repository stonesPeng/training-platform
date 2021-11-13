package me.stone.training.platform.training.java.sci;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author honorStone
 * @description default description
 * @date 2021/11/13 12:04
 */
public interface NumberScaleAdapter {



    static void main(String[] args) {

        try {
            String fileName = "Ag5-mc.txt";
            String fileNameOutput = "Ag5-mc-output.txt";
            Stream<String> lines = Files.lines(Paths.get("F:\\myself\\github\\inner\\training-platform\\training-java\\sci\\src\\main\\java\\me\\stone\\training\\platform\\training\\java\\sci\\" +fileName));
            List<String> collect = lines.map(x -> {
                String[] split = x.split("\\s+");
                double v = Double.parseDouble(split[0]);
                BigDecimal temp = BigDecimal.valueOf(v).setScale(4, RoundingMode.HALF_UP);
                StringBuilder stringBuilder = new StringBuilder();
                return stringBuilder.append(temp)
                    .append("   ")
                    .append(split[1])
                    .toString();
            }).collect(Collectors.toList());

            FileWriter writer = new FileWriter("F:\\myself\\github\\inner\\training-platform\\training-java\\sci\\src\\main\\java\\me\\stone\\training\\platform\\training\\java\\sci\\"+fileNameOutput);
            for(String str: collect) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
