package bukkitrunnablehelper.handlers;

import bukkitrunnablehelper.interfaces.ConditionalRepeatable;

/**
 * Created by Pieter on 2/07/2015.
 */
public class ConditionalRepeatHandler extends RunnableHandler {
    private ConditionalRepeatable conditionalRepeatable;
    public ConditionalRepeatHandler(ConditionalRepeatable timeOutable) {
        super(timeOutable);
        conditionalRepeatable = timeOutable;
        runTaskTimer(plugin, 0, conditionalRepeatable.getDelayTicks());
    }

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
