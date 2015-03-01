package ebunders.validation;

import java.util.function.Predicate;

/**
 * Created by ernst on 26-2-15.
 */
public class MyPredicate<T> implements MessagePredicate<T> {
    private final String message;
    private Predicate<T> wrapped;

    public MyPredicate(Predicate<T>wrapped, String message) {
        this.message = message;
        this.wrapped = wrapped;
    }

    @Override
    public boolean test(T o) {
        return wrapped.test(o);
    }

    public String getMessage() {
        return message;
    }
}
