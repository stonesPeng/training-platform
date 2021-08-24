package me.stone.training.platform.training.component.routers.situation;

import org.springframework.stereotype.Component;

/**
 * @author honorStone
 * @version 1.0
 * @since 2021/3/22 22:32
 */
@Component
public class ClassA {

    private final ClassB classB;

    public ClassA(ClassB classB) {
        this.classB = classB;
    }
}
