package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.LineItemDto;
import com.carlos.invoice.server.model.LineItem;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class LineItemDtoToModelConverter extends RegisterConverter<LineItemDto, LineItem> {

    public LineItemDtoToModelConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public LineItem convert(LineItemDto lineItemDto) {

        LineItem lineItem = new LineItem();
        lineItem.setId(lineItemDto.getId());
        lineItem.setCode(lineItemDto.getCode());
        lineItem.setNumberOfItem(lineItemDto.getNumberOfItem());
        lineItem.setPriceItem(lineItemDto.getPriceItem());
        lineItem.setTotal(lineItemDto.getTotal());

        return lineItem;
    }
}
