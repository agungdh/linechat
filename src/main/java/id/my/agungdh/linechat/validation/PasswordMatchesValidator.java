package id.my.agungdh.linechat.validation;

import id.my.agungdh.linechat.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) obj;
        if (!user.password().equals(user.confirmPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("password") // Explicitly bind to "password" field
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
