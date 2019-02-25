package com.example.crud.bestPractice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PersonalDataDto implements IUpgradableDto {

    private Long id;

    @NotBlank(message = "PersonalDataDto: name is null, empty or blank")
    private String name;

    @NotBlank(message = "PersonalDataDto: subName is null, empty or blank")
    private String subName;

    @NotNull(message = "PersonalDataDto: personalIdentificationDto is null")
    @Valid
    private PersonalIdentificationDto personalIdentificationDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public PersonalIdentificationDto getPersonalIdentificationDto() {
        return personalIdentificationDto;
    }

    public void setPersonalIdentificationDto(PersonalIdentificationDto personalIdentificationDto) {
        this.personalIdentificationDto = personalIdentificationDto;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
