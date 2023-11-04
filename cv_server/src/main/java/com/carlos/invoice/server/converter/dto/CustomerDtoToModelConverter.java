package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.model.Customer;
import com.carlos.invoice.server.model.CustomerIdentification;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToModelConverter extends RegisterConverter<CustomerDto, Customer> {

    public CustomerDtoToModelConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public Customer convert(CustomerDto customerDto) {

        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());

        if (customerDto.getCustomerIdentificationDto() != null) {
            customer.setCustomerIdentification(convert(customerDto.getCustomerIdentificationDto(), CustomerIdentification.class));
        }

        return customer;
    }
}
