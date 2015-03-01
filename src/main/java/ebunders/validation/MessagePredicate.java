package ebunders.validation;

import java.util.function.Predicate;

/**
 * Created by ernst on 26-2-15.
 */
public interface MessagePredicate<T> extends Predicate<T> {
    public String getMessage();
}
