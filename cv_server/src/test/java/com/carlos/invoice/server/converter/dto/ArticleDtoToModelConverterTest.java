package com.carlos.invoice.server.converter.dto;

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
public class ArticleDtoToModelConverterTest {

    private final static Long ID = 14587L;
    private final static String CODE = "4535X";
    private final static Double PRICE = 45.78;
    private final static UnitEnum UNIT = UnitEnum.KG;
    private final static Boolean ACTIVE = true;
    private final static String NAME = "Tomatoes";
    private final static String DESCRIPTION = "Salad Tomatoes";

    @Mock
    private ConversionService conversionService;
    private ArticleDtoToModelConverter articleDtoToModelConverter;

    @Before
    public void setUp() {
        this.articleDtoToModelConverter = new ArticleDtoToModelConverter(this.conversionService);
    }

    @Test
    public void convert_Id_ModelWithId() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);

        when(articleDtoMock.getId()).thenReturn(ID);

        Article article = this.articleDtoToModelConverter.convert(articleDtoMock);

        assertEquals(articleDtoMock.getId(), article.getId());
    }

    @Test
    public void convert_Code_ModelWithCode() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);

        when(articleDtoMock.getCode()).thenReturn(CODE);

        Article article = this.articleDtoToModelConverter.convert(articleDtoMock);

        assertEquals(articleDtoMock.getCode(), article.getCode());
    }

    @Test
    public void convert_Price_ModelWithPrice() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);

        when(articleDtoMock.getPrice()).thenReturn(PRICE);

        Article article = this.articleDtoToModelConverter.convert(articleDtoMock);

        assertEquals(articleDtoMock.getPrice(), article.getPrice());
    }

    @Test
    public void convert_Unit_ModelWithUnit() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);

        when(articleDtoMock.getUnit()).thenReturn(UNIT);

        Article article = this.articleDtoToModelConverter.convert(articleDtoMock);

        assertEquals(articleDtoMock.getUnit(), article.getUnit());
    }

    @Test
    public void convert_Active_ModelWithActive() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);

        when(articleDtoMock.getActive()).thenReturn(ACTIVE);

        Article article = this.articleDtoToModelConverter.convert(articleDtoMock);

        assertEquals(articleDtoMock.getActive(), article.getActive());
    }

    @Test
    public void convert_Name_ModelWithName() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);

        when(articleDtoMock.getName()).thenReturn(NAME);

        Article article = this.articleDtoToModelConverter.convert(articleDtoMock);

        assertEquals(articleDtoMock.getName(), article.getName());
    }

    @Test
    public void convert_Description_ModelWithDescription() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);

        when(articleDtoMock.getDescription()).thenReturn(DESCRIPTION);

        Article article = this.articleDtoToModelConverter.convert(articleDtoMock);

        assertEquals(articleDtoMock.getDescription(), article.getDescription());
    }

}
