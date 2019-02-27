package com.carlos.invoice.server.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.List;

public class ConverterCollectionImpl implements ConverterCollection {

    public List convertList(ConversionService conversionService, List<? extends Object> list, Class vClass) {

        return (List) conversionService.convert(list,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(list.getClass())),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(vClass)));
    }
}
