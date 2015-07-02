package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * A PlannableHandler class extends {@link BukkitRunnable} and handles all classes which are {@link Plannable}. It is a void
 * factory for a {@link TimeOutHandler}, a {@link TimedRepeatableHandler} and a {@link ConditionalRepeatHandler}. The Bukkit {@link JavaPlugin} needs to be
 * set through the {@link PlannableHandler#setPlugin(JavaPlugin)} method before using it in a plugin. After that, classes can be added through the
 * {@link PlannableHandler#handlePlannable(Plannable)} method.
 */
public abstract class PlannableHandler extends BukkitRunnable {
    protected static final List<PlannableHandler> handlers = new ArrayList<PlannableHandler>();
    private Plannable plannable;
    protected static JavaPlugin plugin;

    protected PlannableHandler(Plannable plannable) {
        this.plannable = plannable;
    }

    public static void setPlugin(JavaPlugin plugin) {
        PlannableHandler.plugin = plugin;
    }

    /**
     * A static method which instantiates a new PlannableHandler according the type of the given Plannable. This instance is not
     * returned because the bukkit scheduler handles this by its own.
     *
     * @param plannable The Plannable for which the time out functionality is handled.
     */
    public static void handlePlannable(Plannable plannable) {
        if (plannable instanceof Repeatable) {
            if (plannable instanceof ConditionalRepeatable) {
                handlers.add(new ConditionalRepeatHandler((ConditionalRepeatable) plannable));
            } else if (plannable instanceof TimedRepeatable) {
                handlers.add(new TimedRepeatableHandler((TimedRepeatable) plannable));
            } else {
                handlers.add(new RepeatableHandler(plannable));
            }
        } else if (plannable instanceof TimeOutable) {
            handlers.add(new TimeOutHandler((TimeOutable) plannable));
        }
    }

    /**
     * Cancels the given scheduled {@link Plannable}. If runTimeout is true, the timeout method will be called.
     */
    public static void cancelTimeOut(Plannable plannable, boolean runTimeout) {
        if (runTimeout) plannable.timeout();
        for (PlannableHandler handler : handlers) {
            if (handler.plannable == plannable) handler.cancel();
            break;
        }
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        handlers.remove(this);
    }
}
