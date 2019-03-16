package com.carlos.invoice.server.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;

import javax.annotation.PostConstruct;
import java.util.List;

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

        return ConverterCollection.convertList(this.conversionService, collection, vClass);
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
