package bukkitrunnablehelper.interfaces;

/**
 * Created by Pieter on 2/07/2015.
 */
public interface ConditionalRepeatable extends Repeatable {
    /**
     * The condition which needs to be true for this ConditionalRepeatable to be repeated.
     * @return A boolean with the condition for this ConditionalRepeatable.
     */
    boolean getCondition();

}
