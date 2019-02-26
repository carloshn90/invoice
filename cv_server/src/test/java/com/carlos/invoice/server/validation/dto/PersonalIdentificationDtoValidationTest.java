package com.carlos.invoice.server.validation.dto;

import com.carlos.invoice.server.dto.PersonalIdentificationDto;
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
import static org.junit.Assert.*;

public class PersonalIdentificationDtoValidationTest {

    private Validator validator;
    private List<String> validationMessageList;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        validationMessageList = new ArrayList<>();
        validationMessageList.add("PersonalIdentificationDto: documentTypeEnum is null");
        validationMessageList.add("PersonalIdentificationDto: documentNumber is null, empty or blank");
    }

    @Test
    public void personalIdentificationDto_CheckValidations() {
        PersonalIdentificationDto personalIdentificationDto = new PersonalIdentificationDto();

        Set<ConstraintViolation<PersonalIdentificationDto>> violationSet = this.validator.validate(personalIdentificationDto);

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
