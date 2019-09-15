package com.parent.validation.validators;

import com.parent.validation.constants.TypeEnum;
import org.apache.commons.lang3.EnumUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TypeValidator  implements ConstraintValidator<TypeConstraint, String> {

    /**
     * Verifica daca tipul tranzactiei este unul dintre tipurile acceptate
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return EnumUtils.isValidEnum(TypeEnum.class, value);
    }
}
