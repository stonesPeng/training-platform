package me.stone.training.platform.training.pattern.state;

/**
 * @author honorStone
 * @version 1.0
 * @since 8/19/21 6:15 PM
 */
public class ConcreteStateB extends State{
    @Override
    public void handle1() {
        super.context.setCurrentState(Context.concreteStateA);
        super.context.handle1();
    }

    @Override
    public void handle2() {
        //must do
    }
}
