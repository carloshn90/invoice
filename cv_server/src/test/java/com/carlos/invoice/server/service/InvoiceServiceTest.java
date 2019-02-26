package com.carlos.invoice.server.service;

import com.carlos.invoice.server.dao.InvoiceDao;
import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Mock
    private ConversionService conversionService;

    @Mock
    private InvoiceDao invoiceDao;
    private InvoiceService invoiceService;

    @Before
    public void setUp() {
        this.invoiceService = new InvoiceService(this.conversionService, this.invoiceDao);
    }

    @Test
    public void save_CreationDateInitialize_CreationDateIsCall() {
        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);
        Invoice invoiceMock = mock(Invoice.class);

        when(this.conversionService.convert(invoiceDtoMock, Invoice.class)).thenReturn(invoiceMock);
        when(this.invoiceDao.save(invoiceMock)).thenReturn(invoiceMock);

        this.invoiceService.create(invoiceDtoMock);

        verify(invoiceMock, times(1)).setCreationDate(any(Date.class));
    }

    @Test
    public void save_InvoiceDto_SaveCorrect() {

        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);
        Invoice invoiceMock = mock(Invoice.class);

        when(this.conversionService.convert(invoiceDtoMock, Invoice.class)).thenReturn(invoiceMock);
        when(this.invoiceDao.save(invoiceMock)).thenReturn(invoiceMock);

        this.invoiceService.create(invoiceDtoMock);

        verify(this.conversionService, times(1)).convert(invoiceDtoMock, Invoice.class);
        verify(this.invoiceDao, times(1)).save(invoiceMock);

        InOrder inOrder = inOrder(this.conversionService, this.invoiceDao);
        inOrder.verify(this.conversionService).convert(invoiceDtoMock, Invoice.class);
        inOrder.verify(this.invoiceDao).save(invoiceMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_CreationDateNull_IllegalArgumentException() {
        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);
        Invoice invoiceMock = mock(Invoice.class);

        when(this.conversionService.convert(invoiceDtoMock, Invoice.class)).thenReturn(invoiceMock);
        when(invoiceMock.getCreationDate()).thenReturn(null);

        this.invoiceService.update(invoiceDtoMock);
    }

    @Test()
    public void update_InvoiceDto_Correct() {
        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);
        Invoice invoiceMock = mock(Invoice.class);

        when(this.conversionService.convert(invoiceDtoMock, Invoice.class)).thenReturn(invoiceMock);
        when(invoiceMock.getCreationDate()).thenReturn(new Date());
        when(this.invoiceDao.save(invoiceMock)).thenReturn(invoiceMock);

        this.invoiceService.update(invoiceDtoMock);

        verify(this.conversionService, times(1)).convert(invoiceDtoMock, Invoice.class);
        verify(this.invoiceDao, times(1)).save(invoiceMock);

        InOrder inOrder = inOrder(this.conversionService, this.invoiceDao);
        inOrder.verify(this.conversionService).convert(invoiceDtoMock, Invoice.class);
        inOrder.verify(this.invoiceDao).save(invoiceMock);
    }

    @Test
    public void find_DataBaseListNull_EmptyList() {

        when(this.invoiceDao.findAll()).thenReturn(null);

        List<InvoiceDto> invoiceDtoList = this.invoiceService.find();

        assertTrue(invoiceDtoList.isEmpty());
    }

    @Test
    public void find_DataBaseListEmpty_EmptyList() {

        when(this.invoiceDao.findAll()).thenReturn(Collections.emptyList());

        List<InvoiceDto> invoiceDtoList = this.invoiceService.find();

        assertTrue(invoiceDtoList.isEmpty());
    }

    @Test
    public void find_Correct_ReturnInvoiceDtoList() {

        Invoice invoiceMock = mock(Invoice.class);
        List<Invoice> invoiceListMock = Collections.singletonList(invoiceMock);
        InvoiceDto invoiceDtoMock = mock(InvoiceDto.class);
        List<InvoiceDto> invoiceDtoListMock = Collections.singletonList(invoiceDtoMock);
        TypeDescriptor typeDescriptorFrom = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Invoice.class));
        TypeDescriptor typeDescriptorTo = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(InvoiceDto.class));


        when(this.invoiceDao.findAll()).thenReturn(invoiceListMock);
        when(this.conversionService.convert(invoiceListMock, typeDescriptorFrom, typeDescriptorTo)).thenReturn(invoiceDtoListMock);

        List<InvoiceDto> invoiceDtoResultList = this.invoiceService.find();

        assertEquals(invoiceDtoResultList, invoiceDtoListMock);

        verify(this.invoiceDao, times(1)).findAll();
        verify(this.conversionService, times(1)).convert(invoiceListMock, typeDescriptorFrom, typeDescriptorTo);

        InOrder inOrder = inOrder(this.conversionService, this.invoiceDao);
        inOrder.verify(this.invoiceDao).findAll();
        inOrder.verify(this.conversionService).convert(invoiceListMock, typeDescriptorFrom, typeDescriptorTo);
    }

}
