package com.carlos.invoice.server.converter.model;

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
public class CustomerToDtoConverterTest {

    private final static Long ID = 569874L;
    private final static String NAME = "testName";
    private final static String SURNAME = "testSurname";

    @Mock
    private GenericConversionService conversionService;
    private CustomerDataToDtoConverter customerDataToDtoConverter;

    @Before
    public void setUp() {

        this.customerDataToDtoConverter = new CustomerDataToDtoConverter(this.conversionService);
    }

    @Test
    public void convert_Id_DtoWithId() {

        Customer customerMock = mock(Customer.class);

        when(customerMock.getId()).thenReturn(ID);

        CustomerDto customerDto = this.customerDataToDtoConverter.convert(customerMock);

        assertEquals(customerMock.getId(), customerDto.getId());
    }

    @Test
    public void convert_Name_DtoWithName() {

        Customer customerMock = mock(Customer.class);

        when(customerMock.getName()).thenReturn(NAME);

        CustomerDto customerDto = this.customerDataToDtoConverter.convert(customerMock);

        assertEquals(customerMock.getName(), customerDto.getName());
    }

    @Test
    public void convert_Surname_DtoWithSurname() {

        Customer customerMock = mock(Customer.class);

        when(customerMock.getSurname()).thenReturn(SURNAME);

        CustomerDto customerDto = this.customerDataToDtoConverter.convert(customerMock);

        assertEquals(customerMock.getSurname(), customerDto.getSurname());
    }

    @Test
    public void convert_PersonalIdentification_PersonalIdentificationConverterCall1Time() {

        Customer customerMock = mock(Customer.class);
        CustomerIdentification customerIdentificationMock = mock(CustomerIdentification.class);
        CustomerIdentificationDto customerIdentificationDtoMock = mock(CustomerIdentificationDto.class);

        when(customerMock.getCustomerIdentification()).thenReturn(customerIdentificationMock);
        when(this.conversionService.convert(customerIdentificationMock, CustomerIdentificationDto.class)).thenReturn(customerIdentificationDtoMock);

        this.customerDataToDtoConverter.convert(customerMock);

        verify(this.conversionService, times(1)).convert(customerIdentificationMock, CustomerIdentificationDto.class);
    }

}
