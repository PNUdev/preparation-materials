package main.java.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtil {

    public static <E> void validate(E dto){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<E>> violations = validator.validate(dto);

        if (violations.size() > 0) {
            System.out.println("Validation Error");

            for (ConstraintViolation<E> v : violations) {
                System.out.println(v.getPropertyPath() + ": " + v.getMessage());
            }

        }else {
            System.out.println("Successful validation");
        }
    }

}
