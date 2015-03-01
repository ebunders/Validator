package ebunders.validation;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

import static ebunders.validation.ValidationBuilder.*;

/**
 * Created by ernst on 26-2-15.
 */
public class ValidatorTest {

    @Test
    public void testValidator(){

        Validator.createValidation(Person.class, "test", builder->{
            builder.add("firstName", notEmpty())
                    .add("lastName", size().min(3))
                    .add("age", between(10, 60));
        });




        Person p = new Person("", "i", 3);

        List<ValidtionResult> results = Validator.validate(p, Person.class, "test");
        assertEquals(3, results.size());
    }
}
