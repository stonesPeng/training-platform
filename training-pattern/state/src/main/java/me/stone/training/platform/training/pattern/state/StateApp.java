package me.stone.training.platform.training.pattern.state;

/**
 * @author honorStone
 * @version 1.0
 * @since 8/19/21 6:21 PM
 */
public interface StateApp {

    static void main(String[] args) {
        final Context context = new Context();
        context.setCurrentState(new ConcreteStateA());
        context.handle1();
        context.handle2();
    }
}
