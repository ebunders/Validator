package ebunders.validation;

import java.util.function.Supplier;

/**
 * Created by ernst on 26-2-15.
 */
public class ValidatorSelectionException extends RuntimeException {
    public ValidatorSelectionException(String format) {
    }

    public static void when(Supplier<Boolean> when, String message){
        if(when.get()) throw new ValidatorSelectionException(message);
    }
}
