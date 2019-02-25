package com.example.crud.bestPractice.converter.model;

import com.example.crud.bestPractice.converter.RegisterConverter;
import com.example.crud.bestPractice.dto.PersonalIdentificationDto;
import com.example.crud.bestPractice.model.PersonalIdentification;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class PersonalIdentificationToDtoConverter extends RegisterConverter<PersonalIdentification, PersonalIdentificationDto> {

    public PersonalIdentificationToDtoConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public PersonalIdentificationDto convert(PersonalIdentification personalIdentification) {

        PersonalIdentificationDto personalIdentificationDto = new PersonalIdentificationDto();
        personalIdentificationDto.setId(personalIdentification.getId());
        personalIdentificationDto.setDocumentNumber(personalIdentification.getDocumentNumber());
        personalIdentificationDto.setDocumentTypeEnum(personalIdentification.getDocumentTypeEnum());

        return personalIdentificationDto;
    }
}
