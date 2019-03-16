package com.carlos.invoice.server.converter.dto;

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
public class CustomerIdentificationDtoToModelConverterTest {

    private final static Long ID = 745689L;
    private final static String DOCUMENT_NUMBER = "745689W";

    private CustomerIdentificationDtoToModelConverter customerIdentificationDtoToModelConverter;

    @Before
    public void setUp() {
        this.customerIdentificationDtoToModelConverter = new CustomerIdentificationDtoToModelConverter(new GenericConversionService());
    }

    @Test
    public void convert_Id_ModelWitId() {

        CustomerIdentificationDto customerIdentificationDtoMock = mock(CustomerIdentificationDto.class);

        when(customerIdentificationDtoMock.getId()).thenReturn(ID);

        CustomerIdentification customerIdentification =
                this.customerIdentificationDtoToModelConverter.convert(customerIdentificationDtoMock);

        assertEquals(customerIdentification.getId(), customerIdentificationDtoMock.getId());
    }

    @Test
    public void convert_DocumentTypeEnum_ModelWitDocumentTypeEnum() {

        CustomerIdentificationDto customerIdentificationDtoMock = mock(CustomerIdentificationDto.class);

        when(customerIdentificationDtoMock.getDocumentTypeEnum()).thenReturn(DocumentTypeEnum.DNI);

        CustomerIdentification customerIdentification =
                this.customerIdentificationDtoToModelConverter.convert(customerIdentificationDtoMock);

        assertEquals(customerIdentification.getDocumentTypeEnum(), customerIdentificationDtoMock.getDocumentTypeEnum());
    }

    @Test
    public void convert_DocumentNumber_ModelWitDocumentNumber() {

        CustomerIdentificationDto customerIdentificationDtoMock = mock(CustomerIdentificationDto.class);

        when(customerIdentificationDtoMock.getDocumentNumber()).thenReturn(DOCUMENT_NUMBER);

        CustomerIdentification customerIdentification =
                this.customerIdentificationDtoToModelConverter.convert(customerIdentificationDtoMock);

        assertEquals(customerIdentification.getDocumentNumber(), customerIdentificationDtoMock.getDocumentNumber());
    }

}
