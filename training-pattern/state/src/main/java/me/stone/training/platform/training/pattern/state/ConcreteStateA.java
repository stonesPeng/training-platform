package me.stone.training.platform.training.pattern.state;

/**
 * @author honorStone
 * @version 1.0
 * @since 8/19/21 6:06 PM
 */
public class ConcreteStateA extends State {
    @Override
    public void handle1() {
        //must do
    }

    @Override
    public void handle2() {
        super.context.setCurrentState(Context.concreteStateB);
        super.context.handle2();
    }
}
