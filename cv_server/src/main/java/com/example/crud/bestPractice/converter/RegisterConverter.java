package com.example.crud.bestPractice.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;

import javax.annotation.PostConstruct;
import java.util.*;

public abstract class RegisterConverter<S,T> implements Converter<S, T> {


    private ConversionService conversionService;

    @Autowired
    public RegisterConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    protected <T> T convert(Object obj, Class<T> vClass) {

        return this.conversionService.convert(obj, vClass);
    }

    protected <T> List<T> convert(List<? extends Object> collection, Class<T> vClass) {

        return new ArrayList<T>((LinkedHashSet) this.convert(collection, collection.getClass(), vClass));
    }

    private <T> Object convert(Object collection, Class<?> fromClass, Class<T> toClass) {

        return this.conversionService.convert(collection,
                TypeDescriptor.collection(Collection.class, TypeDescriptor.valueOf(fromClass)),
                TypeDescriptor.collection(Collection.class, TypeDescriptor.valueOf(toClass))
        );
    }

    @PostConstruct
    private void register() {
        if (this.conversionService instanceof ConverterRegistry) {
            ((ConverterRegistry) this.conversionService).addConverter(this);
        } else {
            throw new IllegalStateException("Can't register Converter to ConverterRegistry");
        }
    }
}
