package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.TimeOutable;
import bukkitrunnablehelper.interfaces.Repeatable;

/**
 * A RepeatableHandler extends {@link TimeOutableHandler} and handles the scheduling of a repeated action. The {@link #run()} method will
 * be called repeatedly every {@link Repeatable#getDelayTicks()} ticks until this RepeatableHandler is canceled through {@link #cancel()};
 */
public class RepeatableHandler extends TimeOutableHandler {


    private final Repeatable repeatable;

    protected RepeatableHandler(TimeOutable timeOutable) {
        super(timeOutable);
        this.repeatable = (Repeatable) timeOutable;
        runTaskTimer(plugin,repeatable.getDelayTicks(),0);
    }

    @Override
    public void run() {
        repeatable.repeat();
    }
}
