package com.example.crud.bestPractice.validation.validator;

import com.example.crud.bestPractice.dto.LineItemDto;
import com.example.crud.bestPractice.validation.ValidLineItemDtoListValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ValidLineItemDtoListValidatorTest {

    @Test
    public void isValid_ValuesNull_False() {
        ValidLineItemDtoListValidator validLineItemDtoListValidator = new ValidLineItemDtoListValidator();

        assertFalse(validLineItemDtoListValidator.isValid(null, null));
    }

    @Test
    public void isValid_ValuesEmpty_False() {
        ValidLineItemDtoListValidator validLineItemDtoListValidator = new ValidLineItemDtoListValidator();

        assertFalse(validLineItemDtoListValidator.isValid(Collections.emptyList(), null));
    }

    @Test
    public void isValid_LineItemTotalNull_False() {
        ValidLineItemDtoListValidator validLineItemDtoListValidator = new ValidLineItemDtoListValidator();
        LineItemDto lineItemDtoMock = Mockito.mock(LineItemDto.class);

        Mockito.when(lineItemDtoMock.getTotal()).thenReturn(null);
        Mockito.when(lineItemDtoMock.getNumberOfItem()).thenReturn(2);
        Mockito.when(lineItemDtoMock.getPriceItem()).thenReturn(3.28);

        assertFalse(validLineItemDtoListValidator.isValid(Collections.singletonList(lineItemDtoMock), null));
    }

    @Test
    public void isValid_LineItemNumberOfItemNull_False() {
        ValidLineItemDtoListValidator validLineItemDtoListValidator = new ValidLineItemDtoListValidator();
        LineItemDto lineItemDtoMock = Mockito.mock(LineItemDto.class);

        Mockito.when(lineItemDtoMock.getNumberOfItem()).thenReturn(null);

        assertFalse(validLineItemDtoListValidator.isValid(Collections.singletonList(lineItemDtoMock), null));
    }

    @Test
    public void isValid_LineItemPriceItemNull_False() {
        ValidLineItemDtoListValidator validLineItemDtoListValidator = new ValidLineItemDtoListValidator();
        LineItemDto lineItemDtoMock = Mockito.mock(LineItemDto.class);

        Mockito.when(lineItemDtoMock.getPriceItem()).thenReturn(null);

        assertFalse(validLineItemDtoListValidator.isValid(Collections.singletonList(lineItemDtoMock), null));
    }

    @Test
    public void isValid_LineWrongTotal_False() {
        ValidLineItemDtoListValidator validLineItemDtoListValidator = new ValidLineItemDtoListValidator();
        LineItemDto lineItemDtoMock = Mockito.mock(LineItemDto.class);

        Mockito.when(lineItemDtoMock.getTotal()).thenReturn(4.26);
        Mockito.when(lineItemDtoMock.getNumberOfItem()).thenReturn(2);
        Mockito.when(lineItemDtoMock.getPriceItem()).thenReturn(5.47);

        assertFalse(validLineItemDtoListValidator.isValid(Collections.singletonList(lineItemDtoMock), null));
    }

    @Test
    public void isValid_LineCorrectTotal_True() {
        ValidLineItemDtoListValidator validLineItemDtoListValidator = new ValidLineItemDtoListValidator();
        LineItemDto lineItemDtoMock = Mockito.mock(LineItemDto.class);

        Mockito.when(lineItemDtoMock.getNumberOfItem()).thenReturn(2);
        Mockito.when(lineItemDtoMock.getPriceItem()).thenReturn(5.96);
        Mockito.when(lineItemDtoMock.getTotal()).thenReturn(11.92);

        assertTrue(validLineItemDtoListValidator.isValid(Collections.singletonList(lineItemDtoMock), null));
    }
}
