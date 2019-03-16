package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.CustomerIdentificationDto;
import com.carlos.invoice.server.model.CustomerIdentification;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class CustomerIdentificationDtoToModelConverter extends RegisterConverter<CustomerIdentificationDto, CustomerIdentification> {

    public CustomerIdentificationDtoToModelConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public CustomerIdentification convert(CustomerIdentificationDto customerIdentificationDto) {

        CustomerIdentification customerIdentification = new CustomerIdentification();
        customerIdentification.setId(customerIdentificationDto.getId());
        customerIdentification.setDocumentNumber(customerIdentificationDto.getDocumentNumber());
        customerIdentification.setDocumentTypeEnum(customerIdentificationDto.getDocumentTypeEnum());

        return customerIdentification;
    }
}
