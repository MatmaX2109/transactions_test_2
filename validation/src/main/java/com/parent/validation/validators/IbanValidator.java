package com.parent.validation.validators;

import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IbanValidator implements ConstraintValidator<IbanConstraint, String> {

    /**
     * Verifica daca IBAN-ul este valid
     * @param iban
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String iban, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(iban)){
            return true;
        }
        IBANCheckDigit ibanCheckDigit = new IBANCheckDigit();
        return ibanCheckDigit.isValid(iban);    }
}
