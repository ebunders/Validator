package ebunders.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by ernst on 26-2-15.
 */
public class Validation<T>{
    private final String name;
    private final Class<T> type;
    private List<ValidationRule> rules = new ArrayList<>();

    public Validation(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class getType() {
        return type;
    }

    public List<ValidtionResult> validate(T object) {
        //for each validator rule:
        //find the value
        //validate it.
        List<ValidtionResult> result = new ArrayList<>();
        for (ValidationRule rule : rules) {
            Value value = findValue(object, rule.path);
            if(!rule.predicate.test(value)) result.add(new ValidtionResult(rule.path, object, "tralalala"));
        }
        return new ArrayList<>();
    }

    private Value findValue(T object, String path) {
        return null;
    }

    public void addRule(String path, Predicate<Value> validator) {
        rules.add(new ValidationRule(path, validator));
    }

    private static final class ValidationRule {
        final String path;
        final Predicate<Value> predicate;

        private ValidationRule(String path, Predicate<Value> predicate) {
            this.path = path;
            this.predicate = predicate;
        }
    }
}
