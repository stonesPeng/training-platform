package me.stone.training.platform.training.pattern.state;

import lombok.Getter;
import lombok.Setter;

/**
 * @author honorStone
 * @version 1.0
 * @since 8/19/21 6:05 PM
 */
public class Context {

    public static final ConcreteStateA concreteStateA = new ConcreteStateA();
    public static final ConcreteStateB concreteStateB = new ConcreteStateB();

    @Getter
    private State currentState;

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    public void handle1(){
        this.currentState.handle1();
    }

    public void handle2(){
        this.currentState.handle2();
    }


}
