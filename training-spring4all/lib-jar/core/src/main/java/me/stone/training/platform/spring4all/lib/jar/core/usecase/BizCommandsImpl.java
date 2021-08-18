package me.stone.training.platform.spring4all.lib.jar.core.usecase;

import lombok.AllArgsConstructor;
import me.stone.training.platform.spring4all.lib.jar.api.BizCommands;
import me.stone.training.platform.spring4all.lib.jar.core.port.BizRepository;

import java.util.Map;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/16
 */
@AllArgsConstructor
public class BizCommandsImpl implements BizCommands {

    private final BizRepository bizRepository;

    @Override
    public int saveMe(Map<String, String> params) {
        return bizRepository.saveRecord(new BizRepository.BizRecord(params)).orElseThrow(() -> new IllegalArgumentException("error to get biz record"));
    }
}
