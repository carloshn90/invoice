package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.service.ArticleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void findAll_NotResult_EmptyBodyOk() throws Exception {

        List<ArticleDto> articleDtoList = Collections.emptyList();
        TypeReference<List<ArticleDto>> articleDtoListTypeReference = new TypeReference<List<ArticleDto>>(){};

        when(this.articleService.findAll()).thenReturn(articleDtoList);

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(ARTICLE_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();
        List<ArticleDto> articleDtoResultList = this.objectMapper.readValue(resultJson, articleDtoListTypeReference);
        assertThat(articleDtoResultList, hasSize(0));
    }

    @Test
    public void findAll_Result_ResultInBodyOk() throws Exception {

        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(123L);
        List<ArticleDto> articleDtoList = Collections.singletonList(articleDto);
        TypeReference<List<ArticleDto>> articleDtoListTypeReference = new TypeReference<List<ArticleDto>>(){};

        when(this.articleService.findAll()).thenReturn(articleDtoList);

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(ARTICLE_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();
        List<ArticleDto> articleDtoResultList = this.objectMapper.readValue(resultJson, articleDtoListTypeReference);
        assertThat(articleDtoResultList, hasSize(1));
        assertEquals(articleDto.getId(), articleDtoResultList.get(0).getId());
    }
}
