package com.example.crud.bestPractice.converter.model;

import com.example.crud.bestPractice.converter.RegisterConverter;
import com.example.crud.bestPractice.dto.InvoiceDto;
import com.example.crud.bestPractice.dto.LineItemDto;
import com.example.crud.bestPractice.dto.PersonalDataDto;
import com.example.crud.bestPractice.model.Invoice;
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

        if (invoice.getPersonalData() != null) {
            invoiceDto.setPersonalDataDto(convert(invoice.getPersonalData(), PersonalDataDto.class));
        }

        if (invoice.getLineItemList() != null && !invoice.getLineItemList().isEmpty()) {
            invoiceDto.setLineItemDtoList(convert(invoice.getLineItemList(), LineItemDto.class));
        }

        return invoiceDto;
    }
}
