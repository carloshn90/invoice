package com.carlos.invoice.server.validation.dto;

import com.carlos.invoice.server.dto.CustomerIdentificationDto;
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

public class CustomerIdentificationDtoValidationTest {

    private Validator validator;
    private List<String> validationMessageList;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        validationMessageList = new ArrayList<>();
        validationMessageList.add("CustomerIdentificationDto: documentTypeEnum is null");
        validationMessageList.add("CustomerIdentificationDto: documentNumber is null, empty or blank");
    }

    @Test
    public void customerIdentificationDto_CheckValidations() {
        CustomerIdentificationDto customerIdentificationDto = new CustomerIdentificationDto();

        Set<ConstraintViolation<CustomerIdentificationDto>> violationSet = this.validator.validate(customerIdentificationDto);

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
