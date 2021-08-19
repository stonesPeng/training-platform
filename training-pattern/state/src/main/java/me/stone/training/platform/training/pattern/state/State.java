package me.stone.training.platform.training.pattern.state;

import lombok.Setter;

/**
 * @author honorStone
 * @version 1.0
 * @since 8/19/21 6:05 PM
 */
public abstract class State {

    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void handle1();

    public abstract void handle2();
}
