package me.stone.training.platform.training.java.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author honor_stone@163.com
 * @description default description
 * @date 2021/11/25 17:33
 */
public class NewMap {

    public static HashMap<String, String> maps = new HashMap<>();

    static {
        maps.put("key1", "value1");
        maps.put("key2", "value2");
    }

    public static Map<String, String> initMethod2() {
        return Stream.of(new Object[][]{{"key1", "value1"}}).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
    }
}
