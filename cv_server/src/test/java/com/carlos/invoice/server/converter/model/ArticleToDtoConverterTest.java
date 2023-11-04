package com.carlos.invoice.server.converter.model;

import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.enums.UnitEnum;
import com.carlos.invoice.server.model.Article;
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
public class ArticleToDtoConverterTest {

    private final static Long ID = 14587L;
    private final static String CODE = "4535X";
    private final static Double PRICE = 45.78;
    private final static UnitEnum UNIT = UnitEnum.KG;
    private final static Boolean ACTIVE = true;
    private final static String NAME = "Tomatoes";
    private final static String DESCRIPTION = "Salad Tomatoes";

    @Mock
    private ConversionService conversionService;
    private ArticleToDtoConverter articleToDtoConverter;

    @Before
    public void setUp() {
        this.articleToDtoConverter = new ArticleToDtoConverter(this.conversionService);
    }

    @Test
    public void convert_Id_DtoWithId() {

        Article articleMock = mock(Article.class);

        when(articleMock.getId()).thenReturn(ID);

        ArticleDto articleDto = this.articleToDtoConverter.convert(articleMock);

        assertEquals(articleMock.getId(), articleDto.getId());
    }

    @Test
    public void convert_Code_DtoWithCode() {

        Article articleMock = mock(Article.class);

        when(articleMock.getCode()).thenReturn(CODE);

        ArticleDto articleDto = this.articleToDtoConverter.convert(articleMock);

        assertEquals(articleMock.getCode(), articleDto.getCode());
    }

    @Test
    public void convert_Price_DtoWithPrice() {

        Article articleMock = mock(Article.class);

        when(articleMock.getPrice()).thenReturn(PRICE);

        ArticleDto articleDto = this.articleToDtoConverter.convert(articleMock);

        assertEquals(articleMock.getPrice(), articleDto.getPrice());
    }

    @Test
    public void convert_Unit_DtoWithUnit() {

        Article articleMock = mock(Article.class);

        when(articleMock.getUnit()).thenReturn(UNIT);

        ArticleDto articleDto = this.articleToDtoConverter.convert(articleMock);

        assertEquals(articleMock.getUnit(), articleDto.getUnit());
    }

    @Test
    public void convert_Active_DtoWithActive() {

        Article articleMock = mock(Article.class);

        when(articleMock.getActive()).thenReturn(ACTIVE);

        ArticleDto articleDto = this.articleToDtoConverter.convert(articleMock);

        assertEquals(articleMock.getActive(), articleDto.getActive());
    }

    @Test
    public void convert_Name_DtoWithName() {

        Article articleMock = mock(Article.class);

        when(articleMock.getName()).thenReturn(NAME);

        ArticleDto articleDto = this.articleToDtoConverter.convert(articleMock);

        assertEquals(articleMock.getName(), articleDto.getName());
    }

    @Test
    public void convert_Description_DtoWithDescription() {

        Article articleMock = mock(Article.class);

        when(articleMock.getDescription()).thenReturn(DESCRIPTION);

        ArticleDto articleDto = this.articleToDtoConverter.convert(articleMock);

        assertEquals(articleMock.getDescription(), articleDto.getDescription());
    }

}
