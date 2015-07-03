package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * A TimeOutableHandler class extends {@link BukkitRunnable} and handles all classes which are {@link TimeOutable}. It is a void
 * factory for a {@link PlannableHandler}, a {@link TimedRepeatableHandler} and a {@link ConditionalRepeatHandler}. The Bukkit {@link JavaPlugin} needs to be
 * set through the {@link TimeOutableHandler#setPlugin(JavaPlugin)} method before using it in a plugin. After that, classes can be added through the
 * {@link TimeOutableHandler#handlePlannable(TimeOutable)} method.
 */
public abstract class TimeOutableHandler extends BukkitRunnable {
    protected static final List<TimeOutableHandler> handlers = new ArrayList<TimeOutableHandler>();
    private TimeOutable timeOutable;
    protected static JavaPlugin plugin;

    protected TimeOutableHandler(TimeOutable timeOutable) {
        this.timeOutable = timeOutable;
    }

    public static void setPlugin(JavaPlugin plugin) {
        TimeOutableHandler.plugin = plugin;
    }

    /**
     * A static method which instantiates a new TimeOutableHandler according the type of the given TimeOutable. This instance is not
     * returned because the bukkit scheduler handles this by its own.
     *
     * @param timeOutable The TimeOutable for which the time out functionality is handled.
     */
    public static void handlePlannable(TimeOutable timeOutable) {
        if (timeOutable instanceof Repeatable) {
            if (timeOutable instanceof ConditionalRepeatable) {
                handlers.add(new ConditionalRepeatHandler((ConditionalRepeatable) timeOutable));
            } else if (timeOutable instanceof TimedRepeatable) {
                handlers.add(new TimedRepeatableHandler((TimedRepeatable) timeOutable));
            } else {
                handlers.add(new RepeatableHandler(timeOutable));
            }
        } else if (timeOutable instanceof Plannable) {
            handlers.add(new PlannableHandler((Plannable) timeOutable));
        }
    }

    /**
     * Cancels the given scheduled {@link TimeOutable}. If runTimeout is true, the timeout method will be called.
     */
    public static void cancelTimeOut(TimeOutable timeOutable, boolean runTimeout) {
        if (runTimeout) timeOutable.timeout();
        for (TimeOutableHandler handler : handlers) {
            if (handler.timeOutable == timeOutable) handler.cancel();
            break;
        }
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        handlers.remove(this);
    }
}
