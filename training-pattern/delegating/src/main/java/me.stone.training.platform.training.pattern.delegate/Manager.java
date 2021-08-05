package me.stone.training.platform.training.pattern.delegate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/5
 */
public class Manager {

    /**
     * 分析命令的任务类型
     */
    static final Function<String, String> ANALYSIS_WORK_TYPE = command -> {
        if (command.contains(WorkType.WOOD)) {
            return WorkType.WOOD;
        } else if (command.contains(WorkType.ELECT)) {
            return WorkType.ELECT;
        }
        throw new UnsupportedOperationException();
    };
    final Map<String, Worker> scheduler = new HashMap<>();

    public Manager() {
        scheduler.put(WorkType.WOOD, new WoodWorker());
        scheduler.put(WorkType.ELECT, new ElectWorker());
    }

    public void doing(String command) {
        scheduler.get(ANALYSIS_WORK_TYPE.apply(command)).doingJob(command);
    }

    interface WorkType {
        String WOOD = "Wood";
        String ELECT = "Elect";
    }

}
