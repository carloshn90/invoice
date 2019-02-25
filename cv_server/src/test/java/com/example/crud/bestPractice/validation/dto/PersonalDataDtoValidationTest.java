package com.example.crud.bestPractice.validation.dto;

import com.example.crud.bestPractice.dto.PersonalDataDto;
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

public class PersonalDataDtoValidationTest {

    private Validator validator;
    private List<String> validationMessageList;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        validationMessageList = new ArrayList<>();
        validationMessageList.add("PersonalDataDto: name is null, empty or blank");
        validationMessageList.add("PersonalDataDto: subName is null, empty or blank");
        validationMessageList.add("PersonalDataDto: personalIdentificationDto is null");
    }

    @Test
    public void personalDataDto_CheckValidations() {
        PersonalDataDto personalDataDto = new PersonalDataDto();

        Set<ConstraintViolation<PersonalDataDto>> violationSet = this.validator.validate(personalDataDto);

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
