package ebunders.validation;

import java.util.function.Predicate;

/**
 * Created by ernst on 26-2-15.
 */
public class SizePredicate implements MessagePredicate<Value>{
    //basic validation
    Predicate<Value> composed = value -> value.asString()!= null;
    private final String message;

    SizePredicate(String message) {
        this.message = message;
    }

    @Override
    public boolean test(Value value) {
        return composed.test(value);
    }

    public SizePredicate min(int minLength){
        composed = and(value -> value.asString().length() >= minLength);
        return this;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
