package com.example.crud.bestPractice.converter.dto;

import com.example.crud.bestPractice.dto.PersonalIdentificationDto;
import com.example.crud.bestPractice.enums.DocumentTypeEnum;
import com.example.crud.bestPractice.model.PersonalIdentification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.support.GenericConversionService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonalIdentificationDtoToModelConverterTest {

    private final static Long ID = 745689L;
    private final static String DOCUMENT_NUMBER = "745689W";

    private PersonalIdentificationDtoToModelConverter personalIdentificationDtoToModelConverter;

    @Before
    public void setUp() {
        this.personalIdentificationDtoToModelConverter = new PersonalIdentificationDtoToModelConverter(new GenericConversionService());
    }

    @Test
    public void convert_Id_ModelWitId() {

        PersonalIdentificationDto personalIdentificationDtoMock = mock(PersonalIdentificationDto.class);

        when(personalIdentificationDtoMock.getId()).thenReturn(ID);

        PersonalIdentification personalIdentification =
                this.personalIdentificationDtoToModelConverter.convert(personalIdentificationDtoMock);

        assertEquals(personalIdentification.getId(), personalIdentificationDtoMock.getId());
    }

    @Test
    public void convert_DocumentTypeEnum_ModelWitDocumentTypeEnum() {

        PersonalIdentificationDto personalIdentificationDtoMock = mock(PersonalIdentificationDto.class);

        when(personalIdentificationDtoMock.getDocumentTypeEnum()).thenReturn(DocumentTypeEnum.DNI);

        PersonalIdentification personalIdentification =
                this.personalIdentificationDtoToModelConverter.convert(personalIdentificationDtoMock);

        assertEquals(personalIdentification.getDocumentTypeEnum(), personalIdentificationDtoMock.getDocumentTypeEnum());
    }

    @Test
    public void convert_DocumentNumber_ModelWitDocumentNumber() {

        PersonalIdentificationDto personalIdentificationDtoMock = mock(PersonalIdentificationDto.class);

        when(personalIdentificationDtoMock.getDocumentNumber()).thenReturn(DOCUMENT_NUMBER);

        PersonalIdentification personalIdentification =
                this.personalIdentificationDtoToModelConverter.convert(personalIdentificationDtoMock);

        assertEquals(personalIdentification.getDocumentNumber(), personalIdentificationDtoMock.getDocumentNumber());
    }

}
