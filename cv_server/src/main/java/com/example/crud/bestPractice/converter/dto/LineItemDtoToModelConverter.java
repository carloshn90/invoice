package com.example.crud.bestPractice.converter.dto;

import com.example.crud.bestPractice.converter.RegisterConverter;
import com.example.crud.bestPractice.dto.LineItemDto;
import com.example.crud.bestPractice.model.LineItem;
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
