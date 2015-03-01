package ebunders.validation.rules;

import ebunders.validation.Value;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Immutable validation rule.
 * Construction is package scoped: use the Rules factory methods.
 * Created by ernst on 1-3-15.
 */
public final class SingleValidationRule implements ValidationRule {
    private final String message;
    final Predicate<Value> predicate;

    SingleValidationRule(String message, Predicate<Value> predicate) {
        this.message = message;
        this.predicate = predicate;
    }

    @Override
    public Optional<String> validate(Value value) {
        return predicate.test(value) ? Optional.empty() : Optional.of(message);
    }
}
