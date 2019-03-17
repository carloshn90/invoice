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
import org.springframework.core.convert.TypeDescriptor;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

        InOrder inOrder = inOrder(this.conversionService, this.articleDao);
        inOrder.verify(this.conversionService, times(1)).convert(articleDtoMock, Article.class);
        inOrder.verify(this.articleDao, times(1)).save(articleMock);
    }

    @Test()
    public void update_InvoiceDto_Correct() {
        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Article articleMock = mock(Article.class);

        when(this.conversionService.convert(articleDtoMock, Article.class)).thenReturn(articleMock);
        when(this.articleDao.save(articleMock)).thenReturn(articleMock);

        this.articleService.update(articleDtoMock);

        InOrder inOrder = inOrder(this.conversionService, this.articleDao);
        inOrder.verify(this.conversionService, times(1)).convert(articleDtoMock, Article.class);
        inOrder.verify(this.articleDao, times(1)).save(articleMock);
    }

    @Test
    public void find_EmptyDB_ReturnEmptyList() {

        when(this.articleDao.findAll()).thenReturn(Collections.emptyList());

        List<ArticleDto> articleDtoList = this.articleService.find();

        assertTrue(articleDtoList.isEmpty());
    }

    @Test
    public void find_Correct_ReturnArticleDtoList() {

        Article articleMock = mock(Article.class);
        List<Article> articleListMock = Collections.singletonList(articleMock);
        ArticleDto articleDtoMock = mock(ArticleDto.class);
        List<ArticleDto> articleDtoListMock = Collections.singletonList(articleDtoMock);
        TypeDescriptor typeDescriptorFrom = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(articleListMock.getClass()));
        TypeDescriptor typeDescriptorTo = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ArticleDto.class));


        when(this.articleDao.findAll()).thenReturn(articleListMock);
        when(this.conversionService.convert(articleListMock, typeDescriptorFrom, typeDescriptorTo)).thenReturn(articleDtoListMock);

        List<ArticleDto> invoiceDtoResultList = this.articleService.find();

        assertEquals(invoiceDtoResultList, articleDtoListMock);

        InOrder inOrder = inOrder(this.conversionService, this.articleDao);
        inOrder.verify(this.articleDao, times(1)).findAll();
        inOrder.verify(this.conversionService, times(1))
                .convert(articleListMock, typeDescriptorFrom, typeDescriptorTo);
    }
}
