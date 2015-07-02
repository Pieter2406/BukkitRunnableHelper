package bukkitrunnablehelper;

/**
 * Created by Pieter on 25/06/2015.
 */
public interface Repeatable extends TimeOutable {
    int getRemainingTicks();
    int getDelayTicks();
    void repeat();
}
