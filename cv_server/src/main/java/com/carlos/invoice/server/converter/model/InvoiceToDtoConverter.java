package com.carlos.invoice.server.converter.model;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.dto.LineItemDto;
import com.carlos.invoice.server.model.Invoice;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class InvoiceToDtoConverter extends RegisterConverter<Invoice, InvoiceDto> {

    public InvoiceToDtoConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public InvoiceDto convert(Invoice invoice) {

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoice.getId());
        invoiceDto.setCreationDate(invoice.getCreationDate());
        invoiceDto.setTotal(invoice.getTotal());

        if (invoice.getCustomer() != null) {
            invoiceDto.setCustomerDto(convert(invoice.getCustomer(), CustomerDto.class));
        }

        if (invoice.getLineItemList() != null && !invoice.getLineItemList().isEmpty()) {
            invoiceDto.setLineItemDtoList(convert(invoice.getLineItemList(), LineItemDto.class));
        }

        return invoiceDto;
    }
}
