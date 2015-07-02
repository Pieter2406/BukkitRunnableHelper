package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.Plannable;
import bukkitrunnablehelper.interfaces.Repeatable;

/**
 * A RepeatableHandler extends {@link PlannableHandler} and handles the scheduling of a repeated action. The {@link #run()} method will
 * be called repeatedly every {@link Repeatable#getDelayTicks()} ticks until this RepeatableHandler is canceled through {@link #cancel()};
 */
public class RepeatableHandler extends PlannableHandler {


    private final Repeatable repeatable;

    protected RepeatableHandler(Plannable plannable) {
        super(plannable);
        this.repeatable = (Repeatable) plannable;
        runTaskTimer(plugin,repeatable.getDelayTicks(),0);
    }

    @Override
    public void run() {
        repeatable.repeat();
    }
}
