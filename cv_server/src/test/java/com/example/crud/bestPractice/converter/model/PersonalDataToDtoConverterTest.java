package com.example.crud.bestPractice.converter.model;

import com.example.crud.bestPractice.dto.PersonalDataDto;
import com.example.crud.bestPractice.dto.PersonalIdentificationDto;
import com.example.crud.bestPractice.model.PersonalData;
import com.example.crud.bestPractice.model.PersonalIdentification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.support.GenericConversionService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonalDataToDtoConverterTest {

    private final static Long ID = 569874L;
    private final static String NAME = "testName";
    private final static String SUB_NAME = "testSubName";

    @Mock
    private GenericConversionService conversionService;
    private PersonalDataToDtoConverter personalDataToDtoConverter;

    @Before
    public void setUp() {

        this.personalDataToDtoConverter = new PersonalDataToDtoConverter(this.conversionService);
    }

    @Test
    public void convert_Id_DtoWithId() {

        PersonalData personalDataMock = mock(PersonalData.class);

        when(personalDataMock.getId()).thenReturn(ID);

        PersonalDataDto personalDataDto = this.personalDataToDtoConverter.convert(personalDataMock);

        assertEquals(personalDataDto.getId(), personalDataMock.getId());
    }

    @Test
    public void convert_Name_DtoWithName() {

        PersonalData personalDataMock = mock(PersonalData.class);

        when(personalDataMock.getName()).thenReturn(NAME);

        PersonalDataDto personalDataDto = this.personalDataToDtoConverter.convert(personalDataMock);

        assertEquals(personalDataDto.getName(), personalDataMock.getName());
    }

    @Test
    public void convert_SubName_DtoWithSubName() {

        PersonalData personalDataMock = mock(PersonalData.class);

        when(personalDataMock.getSubName()).thenReturn(SUB_NAME);

        PersonalDataDto personalDataDto = this.personalDataToDtoConverter.convert(personalDataMock);

        assertEquals(personalDataDto.getSubName(), personalDataMock.getSubName());
    }

    @Test
    public void convert_PersonalIdentification_PersonalIdentificationConverterCall1Time() {

        PersonalData personalDataMock = mock(PersonalData.class);
        PersonalIdentification personalIdentificationMock = mock(PersonalIdentification.class);
        PersonalIdentificationDto personalIdentificationDtoMock = mock(PersonalIdentificationDto.class);

        when(personalDataMock.getPersonalIdentification()).thenReturn(personalIdentificationMock);
        when(this.conversionService.convert(personalIdentificationMock, PersonalIdentificationDto.class)).thenReturn(personalIdentificationDtoMock);

        this.personalDataToDtoConverter.convert(personalDataMock);

        verify(this.conversionService, times(1)).convert(personalIdentificationMock, PersonalIdentificationDto.class);
    }

}
