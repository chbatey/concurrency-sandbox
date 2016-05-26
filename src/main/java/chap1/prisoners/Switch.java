package chap1.prisoners;

import java.util.concurrent.atomic.AtomicBoolean;

public class Switch {
    private AtomicBoolean on = new AtomicBoolean(false);

    /**
     * Switches the light on
     * @return if the light was already on
     */
    public boolean switchOn() {
        return on.getAndSet(true);
    }

    /**
     * Switches the light off
     * @return if the light was on
     */
    public boolean off() {
        return on.getAndSet(false);
    }
}
