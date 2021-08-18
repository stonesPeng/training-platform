package me.stone.training.platform.spring4all.lib.jars.api;

import java.util.Map;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/16
 */
public interface BizCommands {
    /**
     * save biz
     * @param params params
     * @return save result
     */
    int saveMe(Map<String, String> params);
}
