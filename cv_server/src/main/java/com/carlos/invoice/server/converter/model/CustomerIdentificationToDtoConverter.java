package com.carlos.invoice.server.converter.model;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.CustomerIdentificationDto;
import com.carlos.invoice.server.model.CustomerIdentification;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class CustomerIdentificationToDtoConverter extends RegisterConverter<CustomerIdentification, CustomerIdentificationDto> {

    public CustomerIdentificationToDtoConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public CustomerIdentificationDto convert(CustomerIdentification customerIdentification) {

        CustomerIdentificationDto customerIdentificationDto = new CustomerIdentificationDto();
        customerIdentificationDto.setId(customerIdentification.getId());
        customerIdentificationDto.setDocumentNumber(customerIdentification.getDocumentNumber());
        customerIdentificationDto.setDocumentTypeEnum(customerIdentification.getDocumentTypeEnum());

        return customerIdentificationDto;
    }
}
