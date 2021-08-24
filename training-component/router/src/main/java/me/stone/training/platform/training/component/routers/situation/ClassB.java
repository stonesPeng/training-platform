package me.stone.training.platform.training.component.routers.situation;

import org.springframework.stereotype.Component;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/22 22:32
 */

@Component
public class ClassB {

    private final ClassA classA;

    public ClassB(ClassA classA) {
        this.classA = classA;
    }
}
