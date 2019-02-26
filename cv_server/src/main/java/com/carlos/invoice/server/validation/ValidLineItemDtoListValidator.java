package com.carlos.invoice.server.validation;

import com.carlos.invoice.server.dto.LineItemDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidLineItemDtoListValidator implements ConstraintValidator<ValidLineItemDtoList, List<LineItemDto>> {

    @Override
    public void initialize(ValidLineItemDtoList constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<LineItemDto> values, ConstraintValidatorContext context) {

        if (values == null || values.isEmpty()) {
            return false;
        }

        for (LineItemDto value : values) {

            if (!this.isTotalValid(value)) {
                return false;
            }
        }

        return true;
    }

    private boolean isTotalValid(LineItemDto value) {

        if (value.getNumberOfItem() == null || value.getPriceItem() == null) {
            return false;
        }

        Double totalExpected = value.getPriceItem() * value.getNumberOfItem();

        return value.getTotal() != null && totalExpected.equals(value.getTotal());
    }
}
