package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.Plannable;
import bukkitrunnablehelper.interfaces.TimeOutable;

/**
 * A PlannableHandler is a BukkitRunnable which schedules a Plannable to be timed out after the specified amount of ticks.
 * Instantiating this class is enough to schedule a timeout of a Plannable. Instantiating this class can only be done by
 * the method {@link TimeOutableHandler#handlePlannable(TimeOutable)}.
 */
public class PlannableHandler extends TimeOutableHandler {
    private Plannable plannable;
    public PlannableHandler(Plannable plannable) {
        super(plannable);
        this.plannable = plannable;
        runTaskLater(plugin, plannable.getTimeOutTicks());
    }

    /**
     * Gets called by the Scheduler and runs the {@link Plannable#timeout()} method after {@link Plannable#getTimeOutTicks()} ticks.
     */
    @Override
    public void run() {
        plannable.timeout();
    }

}

