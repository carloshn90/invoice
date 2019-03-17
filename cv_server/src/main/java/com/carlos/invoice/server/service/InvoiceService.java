package com.carlos.invoice.server.service;

import com.carlos.invoice.server.converter.ConverterCollection;
import com.carlos.invoice.server.dao.InvoiceDao;
import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    public void create(@Valid @NotNull InvoiceDto invoiceDto) {

        logger.info(CLASS + ": create invoice");

        Invoice invoice = this.conversionService.convert(invoiceDto, Invoice.class);

        invoice.setCreationDate(new Date());

        this.invoiceDao.save(invoice);
    }

    public void update(@Valid @NotNull InvoiceDto invoiceDto) {

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

        return ConverterCollection.convertList(this.conversionService, invoiceList, InvoiceDto.class);
    }
}
