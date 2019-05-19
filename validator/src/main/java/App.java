import entity.Student;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class App {

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Student student = new Student();
        student.setAge(1);

        Set<ConstraintViolation<Student>> constraints = validator.validate(student);

        for (ConstraintViolation constraintViolation : constraints) {
            String msg = String.format(constraintViolation.getMessage(), constraintViolation.getPropertyPath());
            System.out.println(msg);
        }
    }


}
