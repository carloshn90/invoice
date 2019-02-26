package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.exception.ExceptionHandlingController;
import com.carlos.invoice.server.service.ArticleService;
import com.carlos.invoice.server.validation.ValidIdExistInDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController("/articles")
public class ArticleController extends ExceptionHandlingController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private static final String CLASS = InvoiceController.class.toString();

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/articles")
    public ResponseEntity create(@RequestBody ArticleDto articleDto) {
        logger.info(CLASS + ": Create article");

        this.articleService.create(articleDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/articles/{article-id}")
    @ValidIdExistInDto(message = "ArticleController: article-id is not present in the articleDto")
    public ResponseEntity update(@PathVariable("article-id") Long articleId, @RequestBody ArticleDto articleDto) {
        logger.info(CLASS + ": Update article " + articleId);

        this.articleService.update(articleDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
