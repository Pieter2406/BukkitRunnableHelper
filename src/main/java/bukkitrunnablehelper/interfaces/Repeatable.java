package bukkitrunnablehelper.interfaces;

/**
 * Created by Pieter on 25/06/2015.
 */
public interface Repeatable extends Runnable {
    /**
     * Returns the amount of ticks before {@link Repeatable#repeat()} method of this Repeatable is called.
     * @return An integer with the amount of ticks between every repetition.
     */
    int getDelayTicks();

    /**
     * The method that will be called periodically with an interval of {@link Repeatable#getDelayTicks()}.
     */
    void repeat();
}
