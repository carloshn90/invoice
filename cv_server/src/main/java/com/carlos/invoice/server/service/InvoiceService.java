package com.carlos.invoice.server.service;

import com.carlos.invoice.server.converter.ConverterCollection;
import com.carlos.invoice.server.dao.InvoiceDao;
import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.model.Invoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Validated
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceService {

    private final ConversionService conversionService;
    private final InvoiceDao invoiceDao;

    public void create(@Valid @NotNull InvoiceDto invoiceDto) {

        log.info("Create invoice");

        Invoice invoice = this.conversionService.convert(invoiceDto, Invoice.class);

        invoice.setCreationDate(new Date());

        this.invoiceDao.save(invoice);
    }

    public void update(@Valid @NotNull InvoiceDto invoiceDto) {

        log.info("Update invoice: " + invoiceDto.getId());

        Invoice invoice = this.conversionService.convert(invoiceDto, Invoice.class);

        if (invoice.getCreationDate() == null) {
            throw new IllegalArgumentException("CreationDate is null");
        }

        this.invoiceDao.save(invoice);
    }

    public List<InvoiceDto> findAll() {

        log.info("FindAll invoices");

        List<Invoice> invoiceList = (List<Invoice>) this.invoiceDao.findAll();

        if (invoiceList.isEmpty()) {
            return new ArrayList<>();
        }

        return ConverterCollection.convertList(this.conversionService, invoiceList, InvoiceDto.class);
    }
}
