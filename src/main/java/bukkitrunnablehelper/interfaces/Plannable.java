package bukkitrunnablehelper.interfaces;

/**
 * If a class implements this interface, it is a Plannable. Meaning that it can be timed out after a specified amount
 * of ticks. A Plannable has a timeout method which specifies what will happen when it is timed out, and a getTimeOutTicks
 * method wchich specifies after how many ticks this Plannable will be timed out.
 */
public interface Plannable extends TimeOutable {
    /**
     * Gets the total amount of ticks that this Plannable will last.
     */
    int getTimeOutTicks();
}
