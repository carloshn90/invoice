package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.model.Customer;
import com.carlos.invoice.server.model.Invoice;
import com.carlos.invoice.server.model.LineItem;
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

        if (invoiceDto.getCustomerDto() != null) {
            invoice.setCustomer(convert(invoiceDto.getCustomerDto(), Customer.class));
            invoice.getCustomer().addInvoice(invoice);
        }

        if (invoiceDto.getLineItemDtoList() != null && !invoiceDto.getLineItemDtoList().isEmpty()) {
            invoice.setLineItemList(convert(invoiceDto.getLineItemDtoList(), LineItem.class));
            invoice.getLineItemList().stream().forEach(lineItem -> lineItem.setInvoice(invoice));
        }

        return invoice;
    }
}
