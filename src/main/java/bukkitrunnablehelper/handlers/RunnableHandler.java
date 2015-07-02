package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.*;
import bukkitrunnablehelper.interfaces.Runnable;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter on 2/07/2015.
 */
public abstract class RunnableHandler extends BukkitRunnable{
    protected static final List<RunnableHandler> handlers = new ArrayList<RunnableHandler>();
    private bukkitrunnablehelper.interfaces.Runnable runnable;
    protected static Plugin plugin;
    protected RunnableHandler(Runnable runnable) {
        this.runnable = runnable;
    }

    public static void setPlugin(Plugin plugin) {
        RunnableHandler.plugin = plugin;
    }

    /**
     * A static method which instantiates a new RunnableHandler according the type of the given TimeOutable. This instance is not
     * returned because the bukkit scheduler handles this by its own.
     * @param runnable The TimeOutable for which the time out functionality is handled.
     */
    public static void handleTimeOut(TimeOutable runnable){
        if(runnable instanceof ConditionalRepeatable){
            handlers.add(new ConditionalRepeatHandler((ConditionalRepeatable) runnable));
        }else if(runnable instanceof TimedRepeatable){
            handlers.add(new TimedRepeatHandler((TimedRepeatable) runnable));
        }else{
            handlers.add(new TimeOutHandler(runnable));
        }
    }

    /**
     * Cancels the given scheduled TimeOutable. If runTimeout is true, the timeout method will be called.
     */
    public static void cancelTimeOut(Runnable runnable, boolean runTimeout){
        if(runTimeout)runnable.timeout();
        for(RunnableHandler handler : handlers){
            if(handler.runnable == runnable)handler.cancel();
        }
    }
}
