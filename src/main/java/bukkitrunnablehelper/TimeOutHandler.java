package bukkitrunnablehelper;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * A TimeOutHandler is a BukkitRunnable which schedules a TimeOutable to be timed out after the specified amount of ticks.
 * Instantiating this class is enough to schedule a timeout of a TimeOutable. Instantiating this class can only be done by
 * the method {@link TimeOutHandler#handleTimeOut(TimeOutable)}.
 */
public class TimeOutHandler extends BukkitRunnable{
    private static final List<TimeOutHandler> handlers = new ArrayList<TimeOutHandler>();
    private TimeOutable timeOutable;
    private boolean isRepeated = false;
    private static Plugin plugin;
    private TimeOutHandler(TimeOutable timeOutable) {
        this.timeOutable = timeOutable;
        runTaskLater(plugin, timeOutable.getTimeOutTicks());
    }
    private TimeOutHandler(Repeatable repeatable){
        this.timeOutable = repeatable;
        isRepeated = true;
        runTaskTimer(plugin, 0, repeatable.getDelayTicks());
    }

    /**
     * Gets called by the Scheduler and runs the {@link TimeOutable#timeout()} method after the scheduled time.
     */
    @Override
    public void run() {
        if(isRepeated){ //Maybe better way later
            Repeatable task =  ((Repeatable) timeOutable);
            if(task.getRemainingTicks() < 0) {
                task.timeout();
                cancel();
            }else{
                task.repeat();
            }
        }else{
            timeOutable.timeout();
        }
    }

    /**
     * A static method which instantiates a new TimeOutHandler with the given TimeOutable.
     * @param timeOutable The TimeOutable for which the time out functionality is handled.
     */
    public static void handleTimeOut(TimeOutable timeOutable){
        if(timeOutable instanceof Repeatable){
            handlers.add(new TimeOutHandler((Repeatable) timeOutable));
        }else{
            handlers.add(new TimeOutHandler(timeOutable));
        }
    }

    private void firstRun(Repeatable task){
        task.repeat();
    }

    /**
     * Cancels the given scheduled TimeOutable. If runTimeout is true, the timeout method will be called.
     */
    public static void cancelTimeOut(TimeOutable timeOutable, boolean runTimeout){
        if(runTimeout)timeOutable.timeout();
        for(TimeOutHandler handler : handlers){
            if(handler.timeOutable == timeOutable)handler.cancel();
        }
    }

    public static void setPlugin(Plugin plugin) {
        TimeOutHandler.plugin = plugin;
    }
}

