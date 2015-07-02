package bukkitrunnablehelper;

/**
 * If a class implements this interface, it is a TimeOutable. Meaning that it can be timed out after a specified amount
 * of ticks. A TimeOutable has a timeout method which specifies what will happen when it is timed out, and a getTimeOutTicks
 * method wchich specifies after how many ticks this TimeOutable will be timed out.
 */
public interface TimeOutable {
    /**
     * The method that will be called when this TimeOutable is timeouted.
     */
    void timeout();

    /**
     * Gets the total amount of ticks that this TimeOutable will last.
     */
    int getTimeOutTicks();
}
