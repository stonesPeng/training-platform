package me.stone.training.platform.spring4all.lib.jar.api;

import java.util.Map;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/16
 */
public interface BizQueries {

    Map<String, String> loadById(long id);
}
