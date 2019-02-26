package com.carlos.invoice.server.converter.model;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.LineItemDto;
import com.carlos.invoice.server.model.LineItem;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class LineItemToDtoConverter extends RegisterConverter<LineItem, LineItemDto> {

    public LineItemToDtoConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public LineItemDto convert(LineItem lineItem) {

        LineItemDto lineItemDto = new LineItemDto();
        lineItemDto.setId(lineItem.getId());
        lineItemDto.setCode(lineItem.getCode());
        lineItemDto.setNumberOfItem(lineItem.getNumberOfItem());
        lineItemDto.setPriceItem(lineItem.getPriceItem());
        lineItemDto.setTotal(lineItem.getTotal());

        return lineItemDto;
    }
}
