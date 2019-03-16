package com.carlos.invoice.server.validation.dto;

import com.carlos.invoice.server.dto.CustomerDto;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CustomerDtoValidationTest {

    private Validator validator;
    private List<String> validationMessageList;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        validationMessageList = new ArrayList<>();
        validationMessageList.add("CustomerDto: name is null, empty or blank");
        validationMessageList.add("CustomerDto: subName is null, empty or blank");
        validationMessageList.add("CustomerDto: customerIdentificationDto is null");
    }

    @Test
    public void customerDto_CheckValidations() {
        CustomerDto customerDto = new CustomerDto();

        Set<ConstraintViolation<CustomerDto>> violationSet = this.validator.validate(customerDto);

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
