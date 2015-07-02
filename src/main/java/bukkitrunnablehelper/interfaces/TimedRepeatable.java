package bukkitrunnablehelper.interfaces;

/**
 * Created by Pieter on 2/07/2015.
 */
public interface TimedRepeatable extends Repeatable {
    /**
     * Returns the remaining amount of ticks before {@link Runnable#timeout()} method of this Repeatable is called.
     * @return An integer with the remaining amount of ticks.
     */
    int getRemainingTicks();
}
