package com.carlos.invoice.server.converter.model;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.dto.CustomerIdentificationDto;
import com.carlos.invoice.server.model.Customer;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataToDtoConverter extends RegisterConverter<Customer, CustomerDto> {

    public CustomerDataToDtoConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public CustomerDto convert(Customer customer) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());

        if (customer.getCustomerIdentification() != null) {
            customerDto.setCustomerIdentificationDto(
                    convert(customer.getCustomerIdentification(), CustomerIdentificationDto.class)
            );
        }

        return customerDto;
    }
}
