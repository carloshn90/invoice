package com.example.crud.bestPractice.converter.model;

import com.example.crud.bestPractice.converter.RegisterConverter;
import com.example.crud.bestPractice.dto.LineItemDto;
import com.example.crud.bestPractice.model.LineItem;
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
