package bukkitrunnablehelper.interfaces;

/**
 * A TimedRepeatable interface extends {@link Repeatable} and specifies how long the repeated action will occur.
 */
public interface TimedRepeatable extends Repeatable {
    /**
     * Returns the remaining amount of ticks before {@link TimeOutable#timeout()} method of this Repeatable is called.
     * @return An integer with the remaining amount of ticks.
     */
    int getRemainingTicks();
}
