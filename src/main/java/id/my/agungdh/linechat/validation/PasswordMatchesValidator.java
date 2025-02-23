package id.my.agungdh.linechat.validation;

import id.my.agungdh.linechat.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDTO> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
        if (dto.password() == null || dto.confirmPassword() == null) {
            return false;
        }
        return dto.password().equals(dto.confirmPassword());
    }
}
