package com.carlos.invoice.server.service;

import com.carlos.invoice.server.dao.ArticleDao;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.matchers.IsStringNotEmptyAndLengthEq;
import com.carlos.invoice.server.model.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {

    @Mock
    private ConversionService conversionService;

    @Mock
    private ArticleDao articleDao;
    private ArticleService articleService;

    @Before
    public void setUp() {
        this.articleService = new ArticleService(this.conversionService, articleDao);
    }

    @Test
    public void save_CreateRandomCode_RandomCodeCreated() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Article articleMock = mock(Article.class);

        when(this.conversionService.convert(articleDtoMock, Article.class)).thenReturn(articleMock);
        when(this.articleDao.save(articleMock)).thenReturn(articleMock);

        this.articleService.create(articleDtoMock);

        verify(articleMock, times(1)).setCode(argThat(new IsStringNotEmptyAndLengthEq(10)));
    }

    @Test
    public void save_ArticleDto_SaveCorrect() {

        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Article articleMock = mock(Article.class);

        when(this.conversionService.convert(articleDtoMock, Article.class)).thenReturn(articleMock);
        when(this.articleDao.save(articleMock)).thenReturn(articleMock);

        this.articleService.create(articleDtoMock);

        verify(this.conversionService, times(1)).convert(articleDtoMock, Article.class);
        verify(this.articleDao, times(1)).save(articleMock);

        InOrder inOrder = inOrder(this.conversionService, this.articleDao);
        inOrder.verify(this.conversionService).convert(articleDtoMock, Article.class);
        inOrder.verify(this.articleDao).save(articleMock);
    }

    @Test()
    public void update_InvoiceDto_Correct() {
        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Article articleMock = mock(Article.class);

        when(this.conversionService.convert(articleDtoMock, Article.class)).thenReturn(articleMock);
        when(this.articleDao.save(articleMock)).thenReturn(articleMock);

        this.articleService.update(articleDtoMock);

        verify(this.conversionService, times(1)).convert(articleDtoMock, Article.class);
        verify(this.articleDao, times(1)).save(articleMock);

        InOrder inOrder = inOrder(this.conversionService, this.articleDao);
        inOrder.verify(this.conversionService).convert(articleDtoMock, Article.class);
        inOrder.verify(this.articleDao).save(articleMock);
    }
}
