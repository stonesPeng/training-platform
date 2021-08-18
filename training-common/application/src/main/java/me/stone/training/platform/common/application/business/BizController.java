package me.stone.training.platform.common.application.business;

import lombok.AllArgsConstructor;
import me.stone.training.platform.spring4all.lib.jars.api.BizCommands;
import me.stone.training.platform.spring4all.lib.jars.api.BizQueries;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/17
 */
@RestController
@RequestMapping("/biz")
@AllArgsConstructor
public class BizController {

    private final BizCommands bizCommands;

    private final BizQueries bizQueries;

    @PostMapping
    public int save(@RequestBody Map<String, String> params) {
        return bizCommands.saveMe(params);
    }

    @GetMapping("/{id}")
    public Map<String, String> loadBy(@PathVariable(value = "id") long id) {
        return bizQueries.loadById(id);
    }
}
