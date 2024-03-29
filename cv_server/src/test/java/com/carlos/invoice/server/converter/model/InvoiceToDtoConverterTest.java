package com.carlos.invoice.server.converter.model;

import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.dto.LineItemDto;
import com.carlos.invoice.server.model.Customer;
import com.carlos.invoice.server.model.Invoice;
import com.carlos.invoice.server.model.LineItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceToDtoConverterTest {
    private final static Long ID = 14587L;
    private final static Date CREATION_DATE = new Date();
    private final static Double TOTAL = 45.78;

    @Mock
    private ConversionService conversionService;
    private InvoiceToDtoConverter invoiceToDtoConverter;

    @Mock
    private List<LineItem> lineItemListMock;

    @Before
    public void setUp() {
        this.invoiceToDtoConverter = new InvoiceToDtoConverter(this.conversionService);
    }

    @Test
    public void convert_Id_DtoWithId() {

        Invoice invoiceMock = mock(Invoice.class);

        when(invoiceMock.getId()).thenReturn(ID);

        InvoiceDto invoiceDto = this.invoiceToDtoConverter.convert(invoiceMock);

        assertEquals(invoiceMock.getId(), invoiceDto.getId());
    }

    @Test
    public void convert_CreationDate_DtoWithCreationDate() {

        Invoice invoiceMock = mock(Invoice.class);

        when(invoiceMock.getCreationDate()).thenReturn(CREATION_DATE);

        InvoiceDto invoiceDto = this.invoiceToDtoConverter.convert(invoiceMock);

        assertEquals(invoiceMock.getCreationDate(), invoiceDto.getCreationDate());
    }

    @Test
    public void convert_Total_DtoWithTotal() {

        Invoice invoiceMock = mock(Invoice.class);

        when(invoiceMock.getTotal()).thenReturn(TOTAL);

        InvoiceDto invoiceDto = this.invoiceToDtoConverter.convert(invoiceMock);

        assertEquals(invoiceMock.getTotal(), invoiceDto.getTotal());
    }

    @Test
    public void convert_PersonalData_PersonalDataConverterCall1Time() {

        Invoice invoiceMock = mock(Invoice.class);
        Customer customerMock = mock(Customer.class);
        CustomerDto customerDtoMock = mock(CustomerDto.class);

        when(invoiceMock.getCustomer()).thenReturn(customerMock);
        when(this.conversionService.convert(customerMock, CustomerDto.class)).thenReturn(customerDtoMock);

        this.invoiceToDtoConverter.convert(invoiceMock);

        verify(this.conversionService, times(1)).convert(customerMock, CustomerDto.class);
    }

    @Test
    public void convert_LineItemList_LineItemConverterCall1Time() {

        Invoice invoiceMock = mock(Invoice.class);
        LineItemDto lineItemDtoMock = mock(LineItemDto.class);
        List<LineItemDto> lineItemDtoList = Collections.singletonList(lineItemDtoMock);
        TypeDescriptor typeDescriptorFrom = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(lineItemListMock.getClass()));
        TypeDescriptor typeDescriptorTo = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(LineItemDto.class));

        when(invoiceMock.getLineItemList()).thenReturn(this.lineItemListMock);
        when(this.conversionService.convert(this.lineItemListMock,typeDescriptorFrom, typeDescriptorTo)).thenReturn(lineItemDtoList);

        this.invoiceToDtoConverter.convert(invoiceMock);

        verify(this.conversionService, times(1)).convert(this.lineItemListMock, typeDescriptorFrom, typeDescriptorTo);
    }
}
