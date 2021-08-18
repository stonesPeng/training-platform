package me.stone.training.platform.spring4all.lib.jars.core.usecase;

import lombok.AllArgsConstructor;
import me.stone.training.platform.spring4all.lib.jars.api.BizQueries;
import me.stone.training.platform.spring4all.lib.jars.core.port.BizRepository;

import java.util.Collections;
import java.util.Map;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/16
 */
@AllArgsConstructor
public class BizQueriesImpl implements BizQueries {
    private final BizRepository bizRepository;

    @Override
    public Map<String, String> loadById(long id) {
        return bizRepository.loadById(id)
            .map(BizRepository.BizRecord::getParams)
            .orElse(Collections.emptyMap());
    }
}
