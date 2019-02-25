package com.example.crud.bestPractice.converter.dto;

import com.example.crud.bestPractice.converter.RegisterConverter;
import com.example.crud.bestPractice.dto.InvoiceDto;
import com.example.crud.bestPractice.model.Invoice;
import com.example.crud.bestPractice.model.LineItem;
import com.example.crud.bestPractice.model.PersonalData;
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
