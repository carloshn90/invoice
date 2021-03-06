package com.carlos.invoice.server.dao;

import com.carlos.invoice.server.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ArticleDao extends CrudRepository<Article, Long> {
}
