package jmaster.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import jmaster.model.UserDTO;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user =  (UserDTO) o;

        if(user.getUsername() == null || user.getUsername().length() == 0){
            errors.rejectValue("username","field.required");
        }
        else if(user.getUsername().length() < 6 && user.getUsername().length() > 20){
            errors.rejectValue("username", "field.username.length");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","field.required");
        if(user.getPassword().length() < 8){
            errors.rejectValue("password","field.password.length");
        }
    }
}
