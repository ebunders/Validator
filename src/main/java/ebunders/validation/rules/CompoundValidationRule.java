package ebunders.validation.rules;

import ebunders.validation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Immutable wrapper for a set of validation rules;
 * Construction is package scoped: use the Rules factory methods.
 * Created by ernst on 1-3-15.
 */
public final class CompoundValidationRule implements ValidationRule {
    private final List<SingleValidationRule> rules = new ArrayList<>();
    private final String message;

    CompoundValidationRule(String message) {
        this.message = message;
    }

    private CompoundValidationRule(String message, List<SingleValidationRule> rules, SingleValidationRule rule) {
        this.message = message;
        this.rules.addAll(rules);
        this.rules.add(rule);
    }

    public CompoundValidationRule add(SingleValidationRule rule) {
        return new CompoundValidationRule(message, rules, rule);
    }

    @Override
    public Optional<String> validate(Value value) {

        String messages = rules.stream()
                .map(rule-> rule.validate(value))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining(":"));

        return messages.length() > 0 ?
                Optional.of(message + "[" + messages + "]") :
                Optional.empty();
    }
}
