package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolationException;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

    private final static String ARTICLE_PATH = "/articles";
//    It is recommended to use spinal-case (which is highlighted by RFC3986), this case is used by Google,
//    PayPal and other big companies.
    private final static String ARTICLES_ID_PATH_VARIABLE = "/articles/{article-id}";

    @InjectMocks
    private ArticleController articleController;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private ArticleService articleService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.articleController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void create_PostExist_Created() throws Exception {

        ArticleDto articleDto = new ArticleDto();
        String invoiceDtoJson = objectMapper.writeValueAsString(articleDto);

        this.mockMvc
                .perform(
                        post(ARTICLE_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated());

    }

    @Test
    public void create_ConstraintViolationException_BadRequest() throws Exception {

        ArticleDto articleDto = new ArticleDto();
        String invoiceDtoJson = objectMapper.writeValueAsString(articleDto);

        doThrow(new ConstraintViolationException(new HashSet<>())).when(this.articleService).create(any(ArticleDto.class));

        this.mockMvc
                .perform(
                        post(ARTICLE_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void update_PutExist_Ok() throws Exception {

        Long articleId = 123L;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(articleId);
        String articleDtoJson = objectMapper.writeValueAsString(articleDto);

        this.mockMvc
                .perform(
                        put(ARTICLES_ID_PATH_VARIABLE, articleId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(articleDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());

    }

    @Test
    public void update_ConstraintViolationException_BadRequest() throws Exception {

        ArticleDto articleDto = new ArticleDto();
        String articleDtoJson = objectMapper.writeValueAsString(articleDto);
        Long articleId = 123L;

        doThrow(new ConstraintViolationException(new HashSet<>())).when(this.articleService).update(any(ArticleDto.class));

        this.mockMvc
                .perform(
                        put(ARTICLES_ID_PATH_VARIABLE, articleId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(articleDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }
}
