package com.carlos.invoice.server.validation.validator;

import com.carlos.invoice.server.dao.ArticleDao;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.model.Article;
import com.carlos.invoice.server.validation.ValidArticleDtoUpdateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidArticleDtoUpdateValidatorTest {

    @Mock
    private ArticleDao articleDao;
    private ValidArticleDtoUpdateValidator validArticleDtoUpdateValidator;

    @Before
    public void setUp() {
        this.validArticleDtoUpdateValidator = new ValidArticleDtoUpdateValidator(articleDao);
    }

    @Test
    public void isValid_ArticleDtoNull_False() {
        ConstraintValidatorContext contextMock = mock(ConstraintValidatorContext.class);

        Boolean result = this.validArticleDtoUpdateValidator.isValid(null, contextMock);

        assertFalse(result);
    }

    @Test
    public void isValid_ArticleDtoIdNull_False() {
        ConstraintValidatorContext contextMock = mock(ConstraintValidatorContext.class);
        ArticleDto articleDtoMock = mock(ArticleDto.class);

        when(articleDtoMock.getId()).thenReturn(null);

        Boolean result = this.validArticleDtoUpdateValidator.isValid(articleDtoMock, contextMock);

        assertFalse(result);
    }

    @Test
    public void isValid_ArticleDtoCodeNull_False() {
        ConstraintValidatorContext contextMock = mock(ConstraintValidatorContext.class);
        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Long articleDtoId = 123L;

        when(articleDtoMock.getId()).thenReturn(articleDtoId);
        when(articleDtoMock.getCode()).thenReturn(null);

        Boolean result = this.validArticleDtoUpdateValidator.isValid(articleDtoMock, contextMock);

        assertFalse(result);
    }

    @Test
    public void isValid_ArticleDtoCodeEmpty_False() {
        ConstraintValidatorContext contextMock = mock(ConstraintValidatorContext.class);
        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Long articleDtoId = 123L;
        String articleDtoCode = "";

        when(articleDtoMock.getId()).thenReturn(articleDtoId);
        when(articleDtoMock.getCode()).thenReturn(articleDtoCode);

        Boolean result = this.validArticleDtoUpdateValidator.isValid(articleDtoMock, contextMock);

        assertFalse(result);
    }

    @Test
    public void isValid_ArticleIdNotExistDB_False() {
        ConstraintValidatorContext contextMock = mock(ConstraintValidatorContext.class);
        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Long articleDtoId = 123L;
        String articleDtoCode = "1225886ASV";

        when(articleDtoMock.getId()).thenReturn(articleDtoId);
        when(articleDtoMock.getCode()).thenReturn(articleDtoCode);
        when(articleDao.findById(articleDtoId)).thenReturn(Optional.empty());

        Boolean result = this.validArticleDtoUpdateValidator.isValid(articleDtoMock, contextMock);

        assertFalse(result);
    }

    @Test
    public void isValid_ArticleIdDBDifferentCode_False() {
        ConstraintValidatorContext contextMock = mock(ConstraintValidatorContext.class);
        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Long articleDtoId = 123L;
        String articleDtoCode = "1225886ASV";
        Article articleMock = mock(Article.class);
        String articleCode = "2256479WQD";

        when(articleDtoMock.getId()).thenReturn(articleDtoId);
        when(articleDtoMock.getCode()).thenReturn(articleDtoCode);
        when(articleDao.findById(articleDtoId)).thenReturn(Optional.of(articleMock));
        when(articleMock.getCode()).thenReturn(articleCode);

        Boolean result = this.validArticleDtoUpdateValidator.isValid(articleDtoMock, contextMock);

        assertFalse(result);
    }

    @Test
    public void isValid_ArticleDtoIdWithCodeExist_True() {
        ConstraintValidatorContext contextMock = mock(ConstraintValidatorContext.class);
        ArticleDto articleDtoMock = mock(ArticleDto.class);
        Long articleDtoId = 123L;
        String articleCode = "1225886ASV";
        Article articleMock = mock(Article.class);

        when(articleDtoMock.getId()).thenReturn(articleDtoId);
        when(articleDtoMock.getCode()).thenReturn(articleCode);
        when(articleDao.findById(articleDtoId)).thenReturn(Optional.of(articleMock));
        when(articleMock.getCode()).thenReturn(articleCode);

        Boolean result = this.validArticleDtoUpdateValidator.isValid(articleDtoMock, contextMock);

        assertTrue(result);
    }

}
