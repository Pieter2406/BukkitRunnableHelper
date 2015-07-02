package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.TimeOutable;

/**
 * A TimeOutHandler is a BukkitRunnable which schedules a TimeOutable to be timed out after the specified amount of ticks.
 * Instantiating this class is enough to schedule a timeout of a TimeOutable. Instantiating this class can only be done by
 * the method {@link RunnableHandler#handleTimeOut(TimeOutable)}.
 */
public class TimeOutHandler extends RunnableHandler{
    private TimeOutable timeOutable;
    public TimeOutHandler(TimeOutable timeOutable) {
        super(timeOutable);
        this.timeOutable = timeOutable;
        runTaskLater(plugin, timeOutable.getTimeOutTicks());
    }

    /**
     * Gets called by the Scheduler and runs the {@link TimeOutable#timeout()} method after the scheduled time.
     */
    @Override
    public void run() {
        timeOutable.timeout();
    }

}

