package me.stone.training.platform.spring4all.enable.endpoint;

import lombok.AllArgsConstructor;
import me.stone.training.platform.spring4all.enable.service.ICommands;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/14 14:22
 */
@RestController
@RequestMapping
@AllArgsConstructor
public class BuzController {

    final ICommands commands;

    @GetMapping("/bean")
    public String fetchById(@RequestParam(value = "id") long id) {
        return commands.fetchValueById(id).orElse("");
    }
}
