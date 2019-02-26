package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.model.Invoice;
import com.carlos.invoice.server.model.LineItem;
import com.carlos.invoice.server.model.PersonalData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDtoToModelConverter extends RegisterConverter<InvoiceDto, Invoice> {

    public InvoiceDtoToModelConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public Invoice convert(InvoiceDto invoiceDto) {

        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.getId());
        invoice.setCreationDate(invoiceDto.getCreationDate());
        invoice.setTotal(invoiceDto.getTotal());

        if (invoiceDto.getPersonalDataDto() != null) {
            invoice.setPersonalData(convert(invoiceDto.getPersonalDataDto(), PersonalData.class));
            invoice.getPersonalData().addInvoice(invoice);
        }

        if (invoiceDto.getLineItemDtoList() != null && !invoiceDto.getLineItemDtoList().isEmpty()) {
            invoice.setLineItemList(convert(invoiceDto.getLineItemDtoList(), LineItem.class));
            invoice.getLineItemList().stream().forEach(lineItem -> lineItem.setInvoice(invoice));
        }

        return invoice;
    }
}
