package ebunders.validation.rules;

import java.util.function.Consumer;

/**
 * Created by ernst on 1-3-15.
 */
public class Rules {

    /**
     * Validates if a String is not null and has greater length than null
     */
    public static ValidationRule notEmpty(){
        return new SingleValidationRule("rule.not.empty", value-> value.asString() != null && value.asString().length() > 0);
    }

    /**
     * Validates the size of a collection
     */
    public static ValidationRule size(Consumer<SizeBuilder> consumer){
        SizeBuilder sb = new SizeBuilder();
        consumer.accept(sb);
        return sb.sizeRule;
    }

    /**
     * Validates the length of a string
     */
    public static ValidationRule length(Consumer<LengthBuilder> consumer){
        LengthBuilder lb = new LengthBuilder();
        consumer.accept(lb);
        return lb.lengthRule;
    }

    /**
     * Validates if an int is between two values
     */
    public static ValidationRule between(int min,int max) {
        return new SingleValidationRule("rule.outside.range", value -> value.asNumber().intValue() >= min && value.asNumber().intValue() <= max);
    }

    /**
     * Validates if an float is between two values
     */
    public static ValidationRule between(float min,float max) {
        return new SingleValidationRule("rule.outside.range", value -> value.asNumber().floatValue() >= min && value.asNumber().floatValue() <= max);
    }




    //TODO size and length builder are too similar.
    public static final class SizeBuilder{
        private CompoundValidationRule sizeRule = new CompoundValidationRule("rule.size");
        public SizeBuilder min(int minSize) {
            sizeRule = sizeRule.add(new SingleValidationRule("rule.size.min", value -> value.asCollection().size() >= minSize));
            return this;
        }

        public SizeBuilder exact(int size) {
            sizeRule = sizeRule.add(new SingleValidationRule("rule.size.exact", value -> value.asCollection().size() == size));
            return this;
        }

        public SizeBuilder max(int maxSize) {
            sizeRule = sizeRule.add(new SingleValidationRule("rule.size.max", value -> value.asCollection().size() <= maxSize));
            return this;
        }
    }

    public static final class LengthBuilder{
        private CompoundValidationRule lengthRule = new CompoundValidationRule("rule.length");
        public LengthBuilder min(int minLength) {
            lengthRule = lengthRule.add(new SingleValidationRule("rule.length.min", value -> value.asString().length() >= minLength));
            return this;
        }

        public LengthBuilder exact(int length) {
            lengthRule = lengthRule.add(new SingleValidationRule("rule.length.exact", value -> value.asString().length() == length));
            return this;
        }

        public LengthBuilder max(int maxLength) {
            lengthRule = lengthRule.add(new SingleValidationRule("rule.length.max", value -> value.asString().length() <= maxLength));
            return this;
        }
    }

}
