package com.carlos.invoice.server.converter.model;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.model.Article;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class ArticleToDtoConverter extends RegisterConverter<Article, ArticleDto> {

    public ArticleToDtoConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public ArticleDto convert(Article article) {

        ArticleDto articleDto = new ArticleDto();

        articleDto.setId(article.getId());
        articleDto.setCode(article.getCode());
        articleDto.setActive(article.getActive());
        articleDto.setPrice(article.getPrice());
        articleDto.setUnit(article.getUnit());
        articleDto.setName(article.getName());
        articleDto.setDescription(article.getDescription());

        return articleDto;
    }
}
