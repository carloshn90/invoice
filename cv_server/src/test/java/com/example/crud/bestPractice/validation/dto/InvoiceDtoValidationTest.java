package com.example.crud.bestPractice.validation.dto;

import com.example.crud.bestPractice.dto.InvoiceDto;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class InvoiceDtoValidationTest {

    private Validator validator;
    private List<String> validationMessageList;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        validationMessageList = new ArrayList<>();
        validationMessageList.add("InvoiceDto: invalid invoiceDto");
        validationMessageList.add("InvoiceDto: PersonalDto is null");
        validationMessageList.add("InvoiceDto: Invalid LineItemDto");
        validationMessageList.add("InvoiceDto: Total is null");
    }

    @Test
    public void invoiceDto_CheckValidations() {
        InvoiceDto invoiceDto = new InvoiceDto();

        Set<ConstraintViolation<InvoiceDto>> violationSet = this.validator.validate(invoiceDto);

        assertThat(violationSet, hasSize(this.validationMessageList.size()));

        Set<String> violationMessageSet = violationSet
                .stream()
                .map(ConstraintViolation::getMessageTemplate)
                .collect(Collectors.toSet());

        for (String validationMessage : this.validationMessageList) {
            assertTrue(violationMessageSet.contains(validationMessage));
        }
    }
}
