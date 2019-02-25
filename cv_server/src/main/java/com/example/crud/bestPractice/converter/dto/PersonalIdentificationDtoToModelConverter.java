package com.example.crud.bestPractice.converter.dto;

import com.example.crud.bestPractice.converter.RegisterConverter;
import com.example.crud.bestPractice.dto.PersonalIdentificationDto;
import com.example.crud.bestPractice.model.PersonalIdentification;
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
