package com.carlos.invoice.server.service;

import com.carlos.invoice.server.dao.ArticleDao;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.model.Article;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@Validated
@Transactional
public class ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private static final String CLASS = ArticleService.class.toString();

    private ConversionService conversionService;
    private ArticleDao articleDao;

    @Autowired
    public ArticleService(ConversionService conversionService, ArticleDao articleDao) {
        this.conversionService = conversionService;
        this.articleDao = articleDao;
    }

    public void create(@Valid ArticleDto articleDto) {

        logger.info(CLASS + ": create article");

        Article article = this.conversionService.convert(articleDto, Article.class);

        article.setCode(this.createRandomCode());

        this.articleDao.save(article);
    }

    private String createRandomCode() {

        return RandomStringUtils.random(7, false, true)
                + RandomStringUtils.random(3, true, false).toUpperCase();
    }
}
