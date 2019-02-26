package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.PersonalDataDto;
import com.carlos.invoice.server.model.PersonalData;
import com.carlos.invoice.server.model.PersonalIdentification;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class PersonalDataDtoToModelConverter extends RegisterConverter<PersonalDataDto, PersonalData> {

    public PersonalDataDtoToModelConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public PersonalData convert(PersonalDataDto personalDataDto) {

        PersonalData personalData = new PersonalData();
        personalData.setId(personalDataDto.getId());
        personalData.setName(personalDataDto.getName());
        personalData.setSubName(personalDataDto.getSubName());

        if (personalDataDto.getPersonalIdentificationDto() != null) {
            personalData.setPersonalIdentification(convert(personalDataDto.getPersonalIdentificationDto(), PersonalIdentification.class));
        }

        return personalData;
    }
}
