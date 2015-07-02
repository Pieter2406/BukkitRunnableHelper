package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.TimedRepeatable;

/**
 * Created by Pieter on 2/07/2015.
 */
public class TimedRepeatHandler extends RunnableHandler {
    private TimedRepeatable repeatable;
    public TimedRepeatHandler(TimedRepeatable repeatable) {
        super(repeatable);
        this.repeatable = repeatable;
        runTaskTimer(plugin, 0, repeatable.getDelayTicks());
    }

    @Override
    public void run() {
        if(repeatable.getRemainingTicks() < 0) {
            repeatable.timeout();
            cancel();
        }else{
            repeatable.repeat();
        }
    }
}
