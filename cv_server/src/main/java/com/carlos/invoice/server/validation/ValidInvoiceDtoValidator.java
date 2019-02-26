package com.carlos.invoice.server.validation;

import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.dto.LineItemDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class ValidInvoiceDtoValidator implements ConstraintValidator<ValidInvoiceDto, InvoiceDto> {

    @Override
    public void initialize(ValidInvoiceDto constraintAnnotation) {

    }

    @Override
    public boolean isValid(InvoiceDto value, ConstraintValidatorContext context) {

        if (value == null || value.getTotal() == null) {
            return false;
        }

        if (value.getLineItemDtoList() == null || value.getLineItemDtoList().isEmpty()) {
            return false;
        }

        Double totalLineItem = value.getLineItemDtoList()
                .stream()
                .filter(lineItemDto -> lineItemDto.getTotal() != null)
                .mapToDouble(LineItemDto::getTotal)
                .sum();

        return totalLineItem.equals(value.getTotal());
    }
}
