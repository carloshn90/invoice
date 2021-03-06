package com.carlos.invoice.server.validation;

import com.carlos.invoice.server.dao.ArticleDao;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValidArticleDtoUpdateValidator implements ConstraintValidator<ValidArticleDtoUpdate, ArticleDto> {

   private final ArticleDao articleDao;

   public void initialize(ValidArticleDtoUpdate constraint) {
   }

   public boolean isValid(ArticleDto articleDto, ConstraintValidatorContext context) {

      if (articleDto == null || articleDto.getId() == null) {
         return false;
      }

      if (articleDto.getCode() == null || articleDto.getCode().isEmpty()) {
         return false;
      }

      Optional<Article> articleOptional = this.articleDao.findById(articleDto.getId());

      return articleOptional.map(article -> article.getCode().equals(articleDto.getCode())).orElse(false);

   }
}
