package me.stone.training.platform.training.java.map;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/5
 */
public class MapApp {

    public static HashMap<String, String> maps = new HashMap<>();

    static {
        maps.put("key1", "value1");
        maps.put("key2", "value2");
    }

    public static Map<String, String> initMethod2() {
        return Stream.of(new Object[][]{{"key1", "value1"}}).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
    }
}
