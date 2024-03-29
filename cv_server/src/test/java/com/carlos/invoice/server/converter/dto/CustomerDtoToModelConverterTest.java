package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.dto.CustomerIdentificationDto;
import com.carlos.invoice.server.model.Customer;
import com.carlos.invoice.server.model.CustomerIdentification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.support.GenericConversionService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDtoToModelConverterTest {

    private final static Long ID = 569874L;
    private final static String NAME = "testName";
    private final static String SURNAME = "testSurname";

    @Mock
    private GenericConversionService conversionService;
    private CustomerDtoToModelConverter customerDtoToModelConverter;

    @Before
    public void setUp() {

        this.customerDtoToModelConverter = new CustomerDtoToModelConverter(this.conversionService);
    }

    @Test
    public void convert_Id_ModelWithId() {

        CustomerDto customerDtoMock = mock(CustomerDto.class);

        when(customerDtoMock.getId()).thenReturn(ID);

        Customer customer = this.customerDtoToModelConverter.convert(customerDtoMock);

        assertEquals(customerDtoMock.getId(), customer.getId());
    }

    @Test
    public void convert_Name_ModelWithName() {

        CustomerDto customerDtoMock = mock(CustomerDto.class);

        when(customerDtoMock.getName()).thenReturn(NAME);

        Customer customer = this.customerDtoToModelConverter.convert(customerDtoMock);

        assertEquals(customerDtoMock.getName(), customer.getName());
    }

    @Test
    public void convert_Surname_ModelWithSurname() {

        CustomerDto customerDtoMock = mock(CustomerDto.class);

        when(customerDtoMock.getSurname()).thenReturn(SURNAME);

        Customer customer = this.customerDtoToModelConverter.convert(customerDtoMock);

        assertEquals(customerDtoMock.getSurname(), customer.getSurname());
    }

    @Test
    public void convert_PersonalIdentificationDto_PersonalIdentificationDtoConverterCall1Time() {

        CustomerDto customerDtoMock = mock(CustomerDto.class);
        CustomerIdentificationDto customerIdentificationDtoMock = mock(CustomerIdentificationDto.class);
        CustomerIdentification customerIdentificationMock = mock(CustomerIdentification.class);

        when(customerDtoMock.getCustomerIdentificationDto()).thenReturn(customerIdentificationDtoMock);
        when(this.conversionService.convert(customerIdentificationDtoMock, CustomerIdentification.class)).thenReturn(customerIdentificationMock);

        this.customerDtoToModelConverter.convert(customerDtoMock);

        verify(this.conversionService, times(1)).convert(customerIdentificationDtoMock, CustomerIdentification.class);
    }
}
