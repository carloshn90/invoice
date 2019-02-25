package com.example.crud.bestPractice.converter.dto;

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
public class LineItemDtoToModelConverterTest {

    private final static Long ID = 36987L;
    private final static Integer NUMBER_OF_ITEM = 3;
    private final static String CODE = "35264l";
    private final static Double PRICE_ITEM = 6.59;
    private final static Double TOTAL = 66.76;

    @Mock
    private ConversionService conversionService;
    private LineItemDtoToModelConverter lineItemDtoToModelConverter;

    @Before
    public void setUp() {
        this.lineItemDtoToModelConverter = new LineItemDtoToModelConverter(this.conversionService);
    }

    @Test
    public void convert_Id_ModelWithId() {

        LineItemDto lineItemDtoMock = mock(LineItemDto.class);

        when(lineItemDtoMock.getId()).thenReturn(ID);

        LineItem lineItem = this.lineItemDtoToModelConverter.convert(lineItemDtoMock);

        assertEquals(lineItem.getId(), lineItemDtoMock.getId());
    }

    @Test
    public void convert_NumberOfItem_ModelWithNumberOfItem() {

        LineItemDto lineItemDtoMock = mock(LineItemDto.class);

        when(lineItemDtoMock.getNumberOfItem()).thenReturn(NUMBER_OF_ITEM);

        LineItem lineItem = this.lineItemDtoToModelConverter.convert(lineItemDtoMock);

        assertEquals(lineItem.getNumberOfItem(), lineItemDtoMock.getNumberOfItem());
    }

    @Test
    public void convert_Code_ModelWithCode() {

        LineItemDto lineItemDtoMock = mock(LineItemDto.class);

        when(lineItemDtoMock.getCode()).thenReturn(CODE);

        LineItem lineItem = this.lineItemDtoToModelConverter.convert(lineItemDtoMock);

        assertEquals(lineItem.getCode(), lineItemDtoMock.getCode());
    }

    @Test
    public void convert_PriceItem_ModelWithPriceItem() {

        LineItemDto lineItemDtoMock = mock(LineItemDto.class);

        when(lineItemDtoMock.getPriceItem()).thenReturn(PRICE_ITEM);

        LineItem lineItem = this.lineItemDtoToModelConverter.convert(lineItemDtoMock);

        assertEquals(lineItem.getPriceItem(), lineItemDtoMock.getPriceItem());
    }

    @Test
    public void convert_Total_ModelWithTotal() {

        LineItemDto lineItemDtoMock = mock(LineItemDto.class);

        when(lineItemDtoMock.getTotal()).thenReturn(TOTAL);

        LineItem lineItem = this.lineItemDtoToModelConverter.convert(lineItemDtoMock);

        assertEquals(lineItem.getTotal(), lineItemDtoMock.getTotal());
    }
}
