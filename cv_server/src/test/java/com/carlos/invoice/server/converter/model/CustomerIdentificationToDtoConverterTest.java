package com.carlos.invoice.server.converter.model;

import com.carlos.invoice.server.dto.CustomerIdentificationDto;
import com.carlos.invoice.server.enums.DocumentTypeEnum;
import com.carlos.invoice.server.model.CustomerIdentification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.support.GenericConversionService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerIdentificationToDtoConverterTest {

    private final static Long ID = 745689L;
    private final static String DOCUMENT_NUMBER = "745689W";

    private CustomerIdentificationToDtoConverter customerIdentificationToDtoConverter;

    @Before
    public void setUp() {
        this.customerIdentificationToDtoConverter = new CustomerIdentificationToDtoConverter(new GenericConversionService());
    }

    @Test
    public void convert_Id_DtoWitId() {

        CustomerIdentification customerIdentificationMock = mock(CustomerIdentification.class);

        when(customerIdentificationMock.getId()).thenReturn(ID);

        CustomerIdentificationDto customerIdentificationDto =
                this.customerIdentificationToDtoConverter.convert(customerIdentificationMock);

        assertEquals(customerIdentificationMock.getId(), customerIdentificationDto.getId());
    }

    @Test
    public void convert_DocumentTypeEnum_DtoWitDocumentTypeEnum() {

        CustomerIdentification customerIdentificationMock = mock(CustomerIdentification.class);

        when(customerIdentificationMock.getDocumentTypeEnum()).thenReturn(DocumentTypeEnum.DNI);

        CustomerIdentificationDto customerIdentificationDto =
                this.customerIdentificationToDtoConverter.convert(customerIdentificationMock);

        assertEquals(customerIdentificationMock.getDocumentTypeEnum(), customerIdentificationDto.getDocumentTypeEnum());
    }

    @Test
    public void convert_DocumentNumber_DtoWitDocumentNumber() {

        CustomerIdentification customerIdentificationMock = mock(CustomerIdentification.class);

        when(customerIdentificationMock.getDocumentNumber()).thenReturn(DOCUMENT_NUMBER);

        CustomerIdentificationDto customerIdentificationDto =
                this.customerIdentificationToDtoConverter.convert(customerIdentificationMock);

        assertEquals(customerIdentificationMock.getDocumentNumber(), customerIdentificationDto.getDocumentNumber());
    }

}
