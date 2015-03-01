package ebunders.validation;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by ernst on 26-2-15.
 */
public class ValidationBuilder<T> {
    private Validation<T> validation;

    ValidationBuilder() {
    }

    public ValidationBuilder add(String name, Predicate<Value> validator) {
        validation.addRule(name, validator);
        return this;
    }

    public  Validation<T> build(Consumer<ValidationBuilder<T>> consumer, String name, Class<T> type){
        validation = new Validation<T>(name, type);
        consumer.accept(this);
        return validation;
    }




    public static Predicate<Value> notEmpty() {
        return new MyPredicate<Value>(value -> value.asString() != null && !value.asString().isEmpty(), "Field can not be empty");
    }

    public static SizePredicate size() {
        return new SizePredicate("Size constraints not met.");
    }

    public static Predicate<Value> between(int min, int max) {
        return new MyPredicate<>(value -> asFloat(value) >= min && asFloat(value) <= max, "Size constraint not met.");
    }


    private static Float asFloat(Value value){return value.asNumber().floatValue();}
}
