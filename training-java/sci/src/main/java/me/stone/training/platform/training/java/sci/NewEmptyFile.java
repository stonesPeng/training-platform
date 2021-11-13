package me.stone.training.platform.training.java.sci;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author honorStone
 * @description default description
 * @date 2021/11/13 11:32
 */
public interface NewEmptyFile {

    String rootPath = "F:\\sci\\";

    List<String> prefixs = Arrays.asList("Pb","C","Ti","Zr","Nb","W");

    List<String> traits = Arrays.asList("-exp","-mc");

    String suffix = ".txt";

    static void main(String[] args) {
        for(int i =5;i<=25;i=i+5){
            for (String trait : traits) {
                for (String prefix : prefixs) {
                    try {
                        //创建文件夹
                        Path path = Paths.get(rootPath+prefix);
                        Files.createDirectories(path);
                        //创建文件
                        String fileName = "\\"+prefix + i + trait+suffix;
                        Files.createFile(Paths.get(rootPath+prefix+fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
