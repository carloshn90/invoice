package com.carlos.invoice.server.service;

import com.carlos.invoice.server.converter.ConverterCollection;
import com.carlos.invoice.server.dao.ArticleDao;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.model.Article;
import com.carlos.invoice.server.validation.ValidArticleDtoUpdate;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Transactional
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private static final String CLASS = ArticleService.class.toString();

    private static final String VALID_ARTICLE_DTO_MESSAGE = "ArticleService: The article code is not valid or doesn't exist for this article";

    private ConversionService conversionService;
    private ArticleDao articleDao;

    @Autowired
    public ArticleService(ConversionService conversionService, ArticleDao articleDao) {
        this.conversionService = conversionService;
        this.articleDao = articleDao;
    }

    public void create(@Valid @NotNull ArticleDto articleDto) {

        logger.info(CLASS + ": create article");

        Article article = this.conversionService.convert(articleDto, Article.class);

        article.setCode(this.createRandomCode());

        this.articleDao.save(article);
    }

    public void update(@Valid @NotNull @ValidArticleDtoUpdate(message = VALID_ARTICLE_DTO_MESSAGE) ArticleDto articleDto) {

        logger.info(CLASS + ": update article");

        Article article = this.conversionService.convert(articleDto, Article.class);

        this.articleDao.save(article);
    }

    public List<ArticleDto> find() {

        List<Article> articleList = (List)this.articleDao.findAll();

        if (articleList.isEmpty()) {
            return new ArrayList<>();
        }

        return ConverterCollection.convertList(this.conversionService, articleList, ArticleDto.class);
    }

    private String createRandomCode() {

        return RandomStringUtils.random(7, false, true)
                + RandomStringUtils.random(3, true, false).toUpperCase();
    }
}
