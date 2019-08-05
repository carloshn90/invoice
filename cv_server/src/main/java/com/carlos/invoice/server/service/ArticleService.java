package com.carlos.invoice.server.service;

import com.carlos.invoice.server.converter.ConverterCollection;
import com.carlos.invoice.server.dao.ArticleDao;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.model.Article;
import com.carlos.invoice.server.validation.ValidArticleDtoUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleService {

    private static final String VALID_ARTICLE_DTO_MESSAGE = "ArticleService: The article code is not valid or doesn't exist for this article";

    private final ConversionService conversionService;
    private final ArticleDao articleDao;

    public void create(@Valid @NotNull ArticleDto articleDto) {

        log.info("Create article");

        Article article = this.conversionService.convert(articleDto, Article.class);

        article.setCode(this.createRandomCode());

        this.articleDao.save(article);
    }

    public void update(@Valid @NotNull @ValidArticleDtoUpdate(message = VALID_ARTICLE_DTO_MESSAGE) ArticleDto articleDto) {

        log.info("Update article");

        Article article = this.conversionService.convert(articleDto, Article.class);

        this.articleDao.save(article);
    }

    public List<ArticleDto> findAll() {

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
