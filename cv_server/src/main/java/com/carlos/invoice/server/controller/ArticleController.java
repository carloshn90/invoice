package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.exception.ExceptionHandlingController;
import com.carlos.invoice.server.service.ArticleService;
import com.carlos.invoice.server.validation.ValidIdExistInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@Slf4j
@RequestMapping("/articles")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController extends ExceptionHandlingController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity create(@RequestBody ArticleDto articleDto) {
        log.info("Create article");

        this.articleService.create(articleDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{article-id}")
    @ValidIdExistInDto(message = "ArticleController: article-id is not present in the articleDto")
    public ResponseEntity update(@PathVariable("article-id") Long articleId, @RequestBody ArticleDto articleDto) {
        log.info("Update article " + articleId);

        this.articleService.update(articleDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<ArticleDto> findAll() {
        log.info("Find all articles");

        return this.articleService.findAll();
    }
}
