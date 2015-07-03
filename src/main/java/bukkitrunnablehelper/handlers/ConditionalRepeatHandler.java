package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.ConditionalRepeatable;
import bukkitrunnablehelper.interfaces.TimeOutable;

/**
 * A ConditionalRepeatHandler extends a {@link RepeatableHandler} and handles the scheduling of a {@link ConditionalRepeatable} class.
 * Until {@link ConditionalRepeatable#getCondition()} is false, the {@link ConditionalRepeatable#repeat()} method is called. If the condition
 * is false, this task will be canceled ({@link #cancel()}) and the method {@link ConditionalRepeatable#timeout()} is called.
 */
public class ConditionalRepeatHandler extends RepeatableHandler {
    private ConditionalRepeatable conditionalRepeatable;
    ConditionalRepeatHandler(ConditionalRepeatable timeOutable) {
        super(timeOutable);
        conditionalRepeatable = timeOutable;
        runTaskTimer(plugin, 0, conditionalRepeatable.getDelayTicks());
    }

    /**
     * This method is called every "{@link ConditionalRepeatable#getDelayTicks()}" ticks. If {@link ConditionalRepeatable#getCondition()} is true
     * this method will call {@link ConditionalRepeatable#repeat()}. Else this method will call {@link ConditionalRepeatable#timeout()} and the {@link TimeOutable} will
     * be cancelled through {@link #cancel()}.
     */
    @Override
    public void run() {
        if(conditionalRepeatable.getCondition()){
            conditionalRepeatable.repeat();
        }else{
            conditionalRepeatable.timeout();
            cancel();
        }
    }
}
