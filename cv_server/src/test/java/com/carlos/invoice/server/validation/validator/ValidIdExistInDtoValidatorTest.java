package com.carlos.invoice.server.validation.validator;

import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.validation.ValidIdExistInDtoValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidIdExistInDtoValidatorTest {

    @Test
    public void isValid_ValuesNull_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(null, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_ValuesEmpty_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        Object[] values = {};
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_ValuesLengthEqualOne_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        Object[] values = {new Long(123)};
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_ValuesLengthEqualThree_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        Object[] values = {new Long(123), new Long(123), new Long(123)};
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_FirstValueNull_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        Object[] values = {null, new Double(135)};
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_SecondValueNull_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        Object[] values = {new Long(123), null};
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_FirstValueDifferentThatLong_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        Object[] values = {new Double(123), new InvoiceDto()};
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_SecondValueDifferentThatIUpgradableDto_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        Object[] values = {new Long(123), new Double(135)};
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_IUpgradableDtoIdNull_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        Object[] values = {new Long(123), new InvoiceDto()};
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_InvoiceIdDifferentIUpgradableDtoId_False() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);
        Object[] values = {new Long(123), invoiceDtoMock};

        when(invoiceDtoMock.getId()).thenReturn(new Long(7887));

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertFalse(isValid);
    }

    @Test
    public void isValid_InvoiceIdEqualIUpgradableDtoId_True() {

        ValidIdExistInDtoValidator validIdExistInDtoValidator = new ValidIdExistInDtoValidator();
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);
        Long invoiceId = new Long(123);
        Object[] values = {invoiceId, invoiceDtoMock};

        when(invoiceDtoMock.getId()).thenReturn(invoiceId);

        boolean isValid = validIdExistInDtoValidator.isValid(values, context);

        assertTrue(isValid);
    }

}
