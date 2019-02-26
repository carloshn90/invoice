package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.dto.LineItemDto;
import com.carlos.invoice.server.dto.PersonalDataDto;
import com.carlos.invoice.server.model.Invoice;
import com.carlos.invoice.server.model.LineItem;
import com.carlos.invoice.server.model.PersonalData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceDtoToModelConverterTest {

    private final static Long ID = 14587L;
    private final static Date CREATION_DATE = new Date();
    private final static Double TOTAL = 45.78;

    @Mock
    private ConversionService conversionService;
    private InvoiceDtoToModelConverter invoiceDtoToModelConverter;

    @Mock
    private List<LineItemDto> lineItemDtoListMock;

    @Before
    public void setUp() {
        this.invoiceDtoToModelConverter = new InvoiceDtoToModelConverter(this.conversionService);
    }

    @Test
    public void convert_Id_ModelWithId() {

        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);

        when(invoiceDtoMock.getId()).thenReturn(ID);

        Invoice invoice = this.invoiceDtoToModelConverter.convert(invoiceDtoMock);

        assertEquals(invoice.getId(), invoiceDtoMock.getId());
    }

    @Test
    public void convert_CreationDate_ModelWithCreationDate() {

        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);

        when(invoiceDtoMock.getCreationDate()).thenReturn(CREATION_DATE);

        Invoice invoice = this.invoiceDtoToModelConverter.convert(invoiceDtoMock);

        assertEquals(invoice.getCreationDate(), invoiceDtoMock.getCreationDate());
    }

    @Test
    public void convert_Total_ModelWithTotal() {

        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);

        when(invoiceDtoMock.getTotal()).thenReturn(TOTAL);

        Invoice invoice = this.invoiceDtoToModelConverter.convert(invoiceDtoMock);

        assertEquals(invoice.getTotal(), invoiceDtoMock.getTotal());
    }

    @Test
    public void convert_PersonalDataDto_PersonalDataDtoConverterCall1Time() {

        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);
        PersonalDataDto personalDataDtoMock = mock(PersonalDataDto.class);
        PersonalData personalDataMock = mock(PersonalData.class);

        when(invoiceDtoMock.getPersonalDataDto()).thenReturn(personalDataDtoMock);
        when(this.conversionService.convert(personalDataDtoMock, PersonalData.class)).thenReturn(personalDataMock);

        this.invoiceDtoToModelConverter.convert(invoiceDtoMock);

        verify(this.conversionService, times(1)).convert(personalDataDtoMock, PersonalData.class);
        verify(personalDataMock, times(1)).addInvoice(any(Invoice.class));
    }

    @Test
    public void convert_LineItemDtoList_LineItemDtoConverterCall1Time() {

        InvoiceDto invoiceDto = mock(InvoiceDto.class);
        LineItem lineItemMock = mock(LineItem.class);
        LinkedHashSet<LineItem> lineItemHashSetMock = new LinkedHashSet<>();
        lineItemHashSetMock.add(lineItemMock);
        TypeDescriptor typeDescriptorFrom = TypeDescriptor.collection(Collection.class, TypeDescriptor.valueOf(this.lineItemDtoListMock.getClass()));
        TypeDescriptor typeDescriptorTo = TypeDescriptor.collection(Collection.class, TypeDescriptor.valueOf(LineItem.class));

        when(invoiceDto.getLineItemDtoList()).thenReturn(this.lineItemDtoListMock);
        when(this.conversionService.convert(this.lineItemDtoListMock,typeDescriptorFrom, typeDescriptorTo)).thenReturn(lineItemHashSetMock);

        this.invoiceDtoToModelConverter.convert(invoiceDto);

        verify(this.conversionService, times(1)).convert(this.lineItemDtoListMock, typeDescriptorFrom, typeDescriptorTo);
        verify(lineItemMock, times(1)).setInvoice(any(Invoice.class));
    }
}
