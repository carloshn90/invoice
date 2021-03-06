package com.carlos.invoice.server.validation.dto;

import com.carlos.invoice.server.dto.LineItemDto;
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

public class LineItemDtoValidationTest {

    private Validator validator;
    private List<String> validationMessageList;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();

        validationMessageList = new ArrayList<>();
        validationMessageList.add("LineItemDto: numberOfItem is null");
        validationMessageList.add("LineItemDto: code is null, empty or blank");
        validationMessageList.add("LineItemDto: priceItem is null");
        validationMessageList.add("LineItemDto: total is null");
    }

    @Test
    public void lineItemDto_CheckValidations() {
        LineItemDto lineItemDto = new LineItemDto();

        Set<ConstraintViolation<LineItemDto>> violationSet = this.validator.validate(lineItemDto);

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
