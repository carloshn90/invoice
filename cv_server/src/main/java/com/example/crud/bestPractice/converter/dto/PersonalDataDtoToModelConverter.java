package com.example.crud.bestPractice.converter.dto;

import com.example.crud.bestPractice.converter.RegisterConverter;
import com.example.crud.bestPractice.dto.PersonalDataDto;
import com.example.crud.bestPractice.model.PersonalData;
import com.example.crud.bestPractice.model.PersonalIdentification;
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
