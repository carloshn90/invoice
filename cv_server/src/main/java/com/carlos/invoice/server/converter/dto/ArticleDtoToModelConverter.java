package com.carlos.invoice.server.converter.dto;

import com.carlos.invoice.server.converter.RegisterConverter;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.model.Article;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class ArticleDtoToModelConverter extends RegisterConverter<ArticleDto, Article> {

    public ArticleDtoToModelConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public Article convert(ArticleDto articleDto) {

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCode(articleDto.getCode());
        article.setPrice(articleDto.getPrice());
        article.setUnit(articleDto.getUnit());
        article.setActive(articleDto.getActive());
        article.setName(articleDto.getName());
        article.setDescription(articleDto.getDescription());

        return article;
    }
}
