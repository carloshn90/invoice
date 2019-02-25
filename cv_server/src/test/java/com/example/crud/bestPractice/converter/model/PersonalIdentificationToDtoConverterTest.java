package com.example.crud.bestPractice.converter.model;

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
public class PersonalIdentificationToDtoConverterTest {

    private final static Long ID = 745689L;
    private final static String DOCUMENT_NUMBER = "745689W";

    private PersonalIdentificationToDtoConverter personalIdentificationToDtoConverter;

    @Before
    public void setUp() {
        this.personalIdentificationToDtoConverter = new PersonalIdentificationToDtoConverter(new GenericConversionService());
    }

    @Test
    public void convert_Id_DtoWitId() {

        PersonalIdentification personalIdentificationMock = mock(PersonalIdentification.class);

        when(personalIdentificationMock.getId()).thenReturn(ID);

        PersonalIdentificationDto personalIdentificationDto =
                this.personalIdentificationToDtoConverter.convert(personalIdentificationMock);

        assertEquals(personalIdentificationDto.getId(), personalIdentificationMock.getId());
    }

    @Test
    public void convert_DocumentTypeEnum_DtoWitDocumentTypeEnum() {

        PersonalIdentification personalIdentificationMock = mock(PersonalIdentification.class);

        when(personalIdentificationMock.getDocumentTypeEnum()).thenReturn(DocumentTypeEnum.DNI);

        PersonalIdentificationDto personalIdentificationDto =
                this.personalIdentificationToDtoConverter.convert(personalIdentificationMock);

        assertEquals(personalIdentificationDto.getDocumentTypeEnum(), personalIdentificationMock.getDocumentTypeEnum());
    }

    @Test
    public void convert_DocumentNumber_DtoWitDocumentNumber() {

        PersonalIdentification personalIdentificationMock = mock(PersonalIdentification.class);

        when(personalIdentificationMock.getDocumentNumber()).thenReturn(DOCUMENT_NUMBER);

        PersonalIdentificationDto personalIdentificationDto =
                this.personalIdentificationToDtoConverter.convert(personalIdentificationMock);

        assertEquals(personalIdentificationDto.getDocumentNumber(), personalIdentificationMock.getDocumentNumber());
    }

}
