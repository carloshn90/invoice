package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.PersonalIdentificationDto;
import com.carlos.invoice.server.model.PersonalIdentification;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class PersonalIdentificationDtoToModelConverter extends RegisterConverter<PersonalIdentificationDto, PersonalIdentification> {

    public PersonalIdentificationDtoToModelConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public PersonalIdentification convert(PersonalIdentificationDto personalIdentificationDto) {

        PersonalIdentification personalIdentification = new PersonalIdentification();
        personalIdentification.setId(personalIdentificationDto.getId());
        personalIdentification.setDocumentNumber(personalIdentificationDto.getDocumentNumber());
        personalIdentification.setDocumentTypeEnum(personalIdentificationDto.getDocumentTypeEnum());

        return personalIdentification;
    }
}
