package ebunders.validation.rules;

import ebunders.validation.Value;

import java.util.Optional;

/**
 * Created by ernst on 1-3-15.
 */
public interface ValidationRule {
    public Optional<String> validate(Value value);
}
