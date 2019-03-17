package com.carlos.invoice.server.service;

import com.carlos.invoice.server.dao.CustomerDao;
import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private ConversionService conversionService;

    @Mock
    private CustomerDao customerDao;
    private CustomerService customerService;

    @Before
    public void setUp() {
        this.customerService = new CustomerService(this.conversionService, this.customerDao);
    }

    @Test
    public void create_CustomerDto_Created() {

        CustomerDto customerDtoMock = mock(CustomerDto.class);
        Customer customerMock = mock(Customer.class);

        when(this.conversionService.convert(customerDtoMock, Customer.class)).thenReturn(customerMock);
        when(this.customerDao.save(customerMock)).thenReturn(customerMock);

        this.customerService.create(customerDtoMock);

        InOrder inOrder = inOrder(this.conversionService, this.customerDao);
        inOrder.verify(this.conversionService, times(1)).convert(customerDtoMock, Customer.class);
        inOrder.verify(this.customerDao, times(1)).save(customerMock);
    }

    @Test
    public void update_CustomerDto_Created() {

        CustomerDto customerDtoMock = mock(CustomerDto.class);
        Customer customerMock = mock(Customer.class);

        when(this.conversionService.convert(customerDtoMock, Customer.class)).thenReturn(customerMock);
        when(this.customerDao.save(customerMock)).thenReturn(customerMock);

        this.customerService.update(customerDtoMock);

        InOrder inOrder = inOrder(this.conversionService, this.customerDao);
        inOrder.verify(this.conversionService, times(1)).convert(customerDtoMock, Customer.class);
        inOrder.verify(this.customerDao, times(1)).save(customerMock);
    }

    @Test
    public void findAll_DataBaseListEmpty_EmptyList() {

        when(this.customerDao.findAll()).thenReturn(Collections.emptyList());

        List<CustomerDto> customerDtoList = this.customerService.findAll();

        assertTrue(customerDtoList.isEmpty());
    }

    @Test
    public void findAll_Correct_ReturnCustomerDtoList() {

        Customer customerMock = mock(Customer.class);
        List<Customer> customerListMock = Collections.singletonList(customerMock);
        CustomerDto customerDtoMock = mock(CustomerDto.class);
        List<CustomerDto> customerDtoListMock = Collections.singletonList(customerDtoMock);
        TypeDescriptor typeDescriptorFrom = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(customerListMock.getClass()));
        TypeDescriptor typeDescriptorTo = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CustomerDto.class));


        when(this.customerDao.findAll()).thenReturn(customerListMock);
        when(this.conversionService.convert(customerListMock, typeDescriptorFrom, typeDescriptorTo)).thenReturn(customerDtoListMock);

        List<CustomerDto> customerDtoResultList = this.customerService.findAll();

        assertEquals(customerDtoResultList, customerDtoListMock);

        InOrder inOrder = inOrder(this.conversionService, this.customerDao);
        inOrder.verify(this.customerDao, times(1)).findAll();
        inOrder.verify(this.conversionService, times(1)).convert(customerListMock, typeDescriptorFrom, typeDescriptorTo);
    }
}
