package com.example.crud.bestPractice.service;

import com.example.crud.bestPractice.dao.InvoiceDao;
import com.example.crud.bestPractice.dto.InvoiceDto;
import com.example.crud.bestPractice.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Validated
@Transactional
public class InvoiceService {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private static final String CLASS = InvoiceService.class.toString();

    private ConversionService conversionService;
    private InvoiceDao invoiceDao;

    @Autowired
    public InvoiceService(ConversionService conversionService, InvoiceDao invoiceDao) {
        this.conversionService = conversionService;
        this.invoiceDao = invoiceDao;
    }

    public void create(@Valid InvoiceDto invoiceDto) {

        logger.info(CLASS + ": create invoice");

        Invoice invoice = this.conversionService.convert(invoiceDto, Invoice.class);

        invoice.setCreationDate(new Date());

        this.invoiceDao.save(invoice);
    }

    public void update(@Valid InvoiceDto invoiceDto) {

        logger.info(CLASS + ": update invoice: " + invoiceDto.getId());

        Invoice invoice = this.conversionService.convert(invoiceDto, Invoice.class);

        if (invoice.getCreationDate() == null) {
            throw new IllegalArgumentException(CLASS + ": CreationDate is null");
        }

        this.invoiceDao.save(invoice);
    }

    public List<InvoiceDto> find() {

        logger.info(CLASS + ": findAll invoices");

        List<Invoice> invoiceList = (List<Invoice>) this.invoiceDao.findAll();

        if (invoiceList == null || invoiceList.isEmpty()) {
            return new ArrayList<>();
        }

        return this.convertInvoiceListToDto(invoiceList);
    }

    private List<InvoiceDto> convertInvoiceListToDto(List<Invoice> invoiceList) {

        return (List<InvoiceDto>) this.conversionService.convert(invoiceList,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Invoice.class)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(InvoiceDto.class)));
    }
}
