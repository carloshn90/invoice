package com.example.crud.bestPractice.converter.model;

import com.example.crud.bestPractice.converter.RegisterConverter;
import com.example.crud.bestPractice.dto.PersonalDataDto;
import com.example.crud.bestPractice.dto.PersonalIdentificationDto;
import com.example.crud.bestPractice.model.PersonalData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class PersonalDataToDtoConverter extends RegisterConverter<PersonalData, PersonalDataDto> {

    public PersonalDataToDtoConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public PersonalDataDto convert(PersonalData personalData) {

        PersonalDataDto personalDataDto = new PersonalDataDto();
        personalDataDto.setId(personalData.getId());
        personalDataDto.setName(personalData.getName());
        personalDataDto.setSubName(personalData.getSubName());

        if (personalData.getPersonalIdentification() != null) {
            personalDataDto.setPersonalIdentificationDto(
                    convert(personalData.getPersonalIdentification(), PersonalIdentificationDto.class)
            );
        }

        return personalDataDto;
    }
}
