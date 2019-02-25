package com.example.crud.bestPractice.converter.model;

import com.example.crud.bestPractice.dto.LineItemDto;
import com.example.crud.bestPractice.model.LineItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LineItemToDtoConverterTest {

    private final static Long ID = 36987L;
    private final static Integer NUMBER_OF_ITEM = 3;
    private final static String CODE = "35264l";
    private final static Double PRICE_ITEM = 6.59;
    private final static Double TOTAL = 66.76;

    @Mock
    private ConversionService conversionService;
    private LineItemToDtoConverter lineItemToDtoConverter;

    @Before
    public void setUp() {
        this.lineItemToDtoConverter = new LineItemToDtoConverter(this.conversionService);
    }

    @Test
    public void convert_Id_DtoWithId() {

        LineItem lineItemMock = mock(LineItem.class);

        when(lineItemMock.getId()).thenReturn(ID);

        LineItemDto lineItemDto = this.lineItemToDtoConverter.convert(lineItemMock);

        assertEquals(lineItemDto.getId(), lineItemMock.getId());
    }

    @Test
    public void convert_NumberOfItem_DtoWithNumberOfItem() {

        LineItem lineItemMock = mock(LineItem.class);

        when(lineItemMock.getNumberOfItem()).thenReturn(NUMBER_OF_ITEM);

        LineItemDto lineItemDto = this.lineItemToDtoConverter.convert(lineItemMock);

        assertEquals(lineItemDto.getNumberOfItem(), lineItemMock.getNumberOfItem());
    }

    @Test
    public void convert_Code_DtoWithCode() {

        LineItem lineItemMock = mock(LineItem.class);

        when(lineItemMock.getCode()).thenReturn(CODE);

        LineItemDto lineItemDto = this.lineItemToDtoConverter.convert(lineItemMock);

        assertEquals(lineItemDto.getCode(), lineItemMock.getCode());
    }

    @Test
    public void convert_PriceItem_DtoWithPriceItem() {

        LineItem lineItemMock = mock(LineItem.class);

        when(lineItemMock.getPriceItem()).thenReturn(PRICE_ITEM);

        LineItemDto lineItemDto = this.lineItemToDtoConverter.convert(lineItemMock);

        assertEquals(lineItemDto.getPriceItem(), lineItemMock.getPriceItem());
    }

    @Test
    public void convert_Total_DtoWithTotal() {

        LineItem lineItemMock = mock(LineItem.class);

        when(lineItemMock.getTotal()).thenReturn(TOTAL);

        LineItemDto lineItemDto = this.lineItemToDtoConverter.convert(lineItemMock);

        assertEquals(lineItemDto.getTotal(), lineItemMock.getTotal());
    }

}
