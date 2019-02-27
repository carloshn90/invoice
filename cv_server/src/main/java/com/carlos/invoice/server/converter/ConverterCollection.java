package com.carlos.invoice.server.converter;

import org.springframework.core.convert.ConversionService;

import java.util.List;

public interface ConverterCollection {

    static List convertList(ConversionService conversionService, List<? extends Object> list, Class vClass) {

        return new ConverterCollectionImpl().convertList(conversionService, list, vClass);
    }
}
