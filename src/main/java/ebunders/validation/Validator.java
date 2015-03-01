package ebunders.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by ernst on 26-2-15.
 */
public class Validator {
    private static final List<Validation<?>> validations = new ArrayList<>();

    public static <T> void createValidation(
            Class<T> type,
            String name,
            Consumer<ValidationBuilder<T>> consumer) {
        validations.add(new ValidationBuilder<T>().build(consumer, name, type));
    }


    //TODO: why can't we induce type from obj.getClass()?
   public  static <T> List<ValidtionResult> validate(T obj, Class<T> type, String validationName) {
        return findValidation( type, validationName).validate(obj);
    }

    /**
     * Tries to find exactly one validation for a given name and a given type
     * @param type
     * @param validationName
     * @param <T>
     *     @Throws ValidatorSelectionException when no or more than one validations were found for given name and type
     * @return
     */
    private static <T> Validation<T> findValidation(Class<T> type, String validationName) {
        List<Validation<T>> applicable = validations.stream()
                .filter(validation -> validationName.equals(validation.getName()))
                .filter(validation -> validation.getType() == type)
                .map(validation-> (Validation<T>) validation)//what to do about this?
                .collect(Collectors.toList());

        ValidatorSelectionException.when(() -> applicable.size() > 1,
                String.format("more than one validator found for type %s and name %s", type.getName(), validationName));

        ValidatorSelectionException.when(applicable::isEmpty,
                String.format("no validator found for type %s and name %s", type.getName(), validationName));

        return applicable.get(0);
    }







}
