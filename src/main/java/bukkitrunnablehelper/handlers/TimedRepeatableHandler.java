package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.Plannable;
import bukkitrunnablehelper.interfaces.TimedRepeatable;

/**
 * TimedRepeatableHandler extends a {@link RepeatableHandler} and handles the scheduling of a {@link TimedRepeatable} class.
 * Every {@link TimedRepeatable#getDelayTicks()} ticks, the {@link TimedRepeatable#repeat()} is called until {@link TimedRepeatable#getRemainingTicks()} is smaller
 * or equal to zero.
 */
public class TimedRepeatableHandler extends RepeatableHandler {
    private TimedRepeatable repeatable;
    TimedRepeatableHandler(TimedRepeatable repeatable) {
        super(repeatable);
        this.repeatable = repeatable;
        runTaskTimer(plugin, 0, repeatable.getDelayTicks());
    }

    /**
     * This method is called every "{@link TimedRepeatable#getDelayTicks()}" ticks. If {@link TimedRepeatable#getRemainingTicks()} is smaller or equal
     * to zero this method will call {@link TimedRepeatable#repeat()}. Else this method will call {@link TimedRepeatable#timeout()} and the {@link Plannable} will
     * be cancelled through {@link #cancel()}.
     */
    @Override
    public void run() {
        if(repeatable.getRemainingTicks() <= 0) {
            repeatable.timeout();
            cancel();
        }else{
            repeatable.repeat();
        }
    }
}
