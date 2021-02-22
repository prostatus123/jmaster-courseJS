package jmaster.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import jmaster.model.ProductDTO;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductDTO product = (ProductDTO) o;

        ValidationUtils.rejectIfEmpty(errors, "name", "field.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "field.required");
        if (product.getPrice() < 0) {
            errors.rejectValue("price", "price.invalid");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"quantity","field.required");
        if(product.getQuantity() <0){
            errors.rejectValue("quantity","quantity.invalid");
        }
    }
}
