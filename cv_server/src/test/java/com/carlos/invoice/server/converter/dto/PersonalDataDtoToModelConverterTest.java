package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.dto.PersonalDataDto;
import com.carlos.invoice.server.dto.PersonalIdentificationDto;
import com.carlos.invoice.server.model.PersonalData;
import com.carlos.invoice.server.model.PersonalIdentification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.support.GenericConversionService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonalDataDtoToModelConverterTest {

    private final static Long ID = 569874L;
    private final static String NAME = "testName";
    private final static String SUB_NAME = "testSubName";

    @Mock
    private GenericConversionService conversionService;
    private PersonalDataDtoToModelConverter personalDataDtoToModelConverter;

    @Before
    public void setUp() {

        this.personalDataDtoToModelConverter = new PersonalDataDtoToModelConverter(this.conversionService);
    }

    @Test
    public void convert_Id_ModelWithId() {

        PersonalDataDto personalDataDtoMock = mock(PersonalDataDto.class);

        when(personalDataDtoMock.getId()).thenReturn(ID);

        PersonalData personalData = this.personalDataDtoToModelConverter.convert(personalDataDtoMock);

        assertEquals(personalData.getId(), personalDataDtoMock.getId());
    }

    @Test
    public void convert_Name_ModelWithName() {

        PersonalDataDto personalDataDtoMock = mock(PersonalDataDto.class);

        when(personalDataDtoMock.getName()).thenReturn(NAME);

        PersonalData personalData = this.personalDataDtoToModelConverter.convert(personalDataDtoMock);

        assertEquals(personalData.getName(), personalDataDtoMock.getName());
    }

    @Test
    public void convert_SubName_ModelWithSubName() {

        PersonalDataDto personalDataDtoMock = mock(PersonalDataDto.class);

        when(personalDataDtoMock.getSubName()).thenReturn(SUB_NAME);

        PersonalData personalData = this.personalDataDtoToModelConverter.convert(personalDataDtoMock);

        assertEquals(personalData.getSubName(), personalDataDtoMock.getSubName());
    }

    @Test
    public void convert_PersonalIdentificationDto_PersonalIdentificationDtoConverterCall1Time() {

        PersonalDataDto personalDataDtoMock = mock(PersonalDataDto.class);
        PersonalIdentificationDto personalIdentificationDtoMock = mock(PersonalIdentificationDto.class);
        PersonalIdentification personalIdentificationMock = mock(PersonalIdentification.class);

        when(personalDataDtoMock.getPersonalIdentificationDto()).thenReturn(personalIdentificationDtoMock);
        when(this.conversionService.convert(personalIdentificationDtoMock, PersonalIdentification.class)).thenReturn(personalIdentificationMock);

        this.personalDataDtoToModelConverter.convert(personalDataDtoMock);

        verify(this.conversionService, times(1)).convert(personalIdentificationDtoMock, PersonalIdentification.class);
    }
}
