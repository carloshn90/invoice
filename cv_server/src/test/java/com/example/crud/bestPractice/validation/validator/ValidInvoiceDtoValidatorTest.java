package com.example.crud.bestPractice.validation.validator;

import com.example.crud.bestPractice.dto.InvoiceDto;
import com.example.crud.bestPractice.dto.LineItemDto;
import com.example.crud.bestPractice.validation.ValidInvoiceDtoValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ValidInvoiceDtoValidatorTest {

    @Test
    public void isValid_InvoiceDtoNull_False() {
        ValidInvoiceDtoValidator validInvoiceDtoValidator = new ValidInvoiceDtoValidator();

        assertFalse(validInvoiceDtoValidator.isValid(null, null));
    }

    @Test
    public void isValid_InvoiceDtoLineItemDtoNull_False() {
        ValidInvoiceDtoValidator validInvoiceDtoValidator = new ValidInvoiceDtoValidator();
        InvoiceDto invoiceDtoMock = Mockito.mock(InvoiceDto.class);

        Mockito.when(invoiceDtoMock.getLineItemDtoList()).thenReturn(null);

        assertFalse(validInvoiceDtoValidator.isValid(invoiceDtoMock, null));
    }

    @Test
    public void isValid_InvoiceDtoLineItemDtoEmpty_False() {
        ValidInvoiceDtoValidator validInvoiceDtoValidator = new ValidInvoiceDtoValidator();
        InvoiceDto invoiceDtoMock = Mockito.mock(InvoiceDto.class);

        Mockito.when(invoiceDtoMock.getLineItemDtoList()).thenReturn(Collections.emptyList());

        assertFalse(validInvoiceDtoValidator.isValid(invoiceDtoMock, null));
    }

    @Test
    public void isValid_InvoiceDtoLineItemDtoTotalNull_False() {
        ValidInvoiceDtoValidator validInvoiceDtoValidator = new ValidInvoiceDtoValidator();
        InvoiceDto invoiceDtoMock = Mockito.mock(InvoiceDto.class);
        LineItemDto lineItemDtoMock = Mockito.mock(LineItemDto.class);

        Mockito.when(lineItemDtoMock.getTotal()).thenReturn(null);
        Mockito.when(invoiceDtoMock.getLineItemDtoList()).thenReturn(Collections.singletonList(lineItemDtoMock));
        Mockito.when(invoiceDtoMock.getTotal()).thenReturn(5.55);

        assertFalse(validInvoiceDtoValidator.isValid(invoiceDtoMock, null));
    }

    @Test
    public void isValid_InvoiceDtoTotalNull_False() {
        ValidInvoiceDtoValidator validInvoiceDtoValidator = new ValidInvoiceDtoValidator();
        InvoiceDto invoiceDtoMock = Mockito.mock(InvoiceDto.class);

        Mockito.when(invoiceDtoMock.getTotal()).thenReturn(null);

        assertFalse(validInvoiceDtoValidator.isValid(invoiceDtoMock, null));
    }

    @Test
    public void isValid_InvoiceDtoWrongTotal_False() {
        ValidInvoiceDtoValidator validInvoiceDtoValidator = new ValidInvoiceDtoValidator();
        InvoiceDto invoiceDtoMock = Mockito.mock(InvoiceDto.class);
        LineItemDto lineItemDtoMock = Mockito.mock(LineItemDto.class);

        Mockito.when(lineItemDtoMock.getTotal()).thenReturn(5.45);
        Mockito.when(invoiceDtoMock.getLineItemDtoList()).thenReturn(Collections.singletonList(lineItemDtoMock));
        Mockito.when(invoiceDtoMock.getTotal()).thenReturn(6.78);

        assertFalse(validInvoiceDtoValidator.isValid(invoiceDtoMock, null));
    }

    @Test
    public void isValid_InvoiceDtoCorrectTotal_True() {
        ValidInvoiceDtoValidator validInvoiceDtoValidator = new ValidInvoiceDtoValidator();
        InvoiceDto invoiceDtoMock = Mockito.mock(InvoiceDto.class);
        LineItemDto lineItemDtoMock = Mockito.mock(LineItemDto.class);

        Mockito.when(lineItemDtoMock.getTotal()).thenReturn(51.43);
        Mockito.when(invoiceDtoMock.getLineItemDtoList()).thenReturn(Collections.singletonList(lineItemDtoMock));
        Mockito.when(invoiceDtoMock.getTotal()).thenReturn(51.43);

        assertTrue(validInvoiceDtoValidator.isValid(invoiceDtoMock, null));
    }
}
