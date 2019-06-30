package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.Application.Constants;
import com.carlos.invoice.server.dto.ArticleDto;
import com.carlos.invoice.server.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ArticleControllerIT {

    private final static String ARTICLE_PATH = "/articles";
//    It is recommended to use spinal-case (which is highlighted by RFC3986), this case is used by Google,
//    PayPal and other big companies.
    private final static String ARTICLE_ID_PATH_VARIABLE = "/articles/{article-id}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    private String token;

    @Before
    public void setUp() {
        this.token = Constants.JWT.TOKEN_PREFIX + " " + this.jwtService.createValidJwtToken();
    }

    @Test
    public void update_CheckIdValidationError_BadRequest() throws Exception {

        ArticleDto articleDto = new ArticleDto();
        String articleDtoJson = objectMapper.writeValueAsString(articleDto);
        Long articleId = 123L;

        this.mockMvc
                .perform(
                        put(ARTICLE_ID_PATH_VARIABLE, articleId)
                                .header(Constants.JWT.TOKEN_HEADER, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(articleDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void create_ArticleToUpdateJson_Ok() throws Exception {

        String articleDtoJson = "{\"price\":12.36,\"unit\":\"KG\",\"active\":true,\"name\":" +
                "\"Tomatoes\",\"description\":\"Salad Tomatoes\"}";

        this.mockMvc
                .perform(
                        post(ARTICLE_PATH)
                                .header(Constants.JWT.TOKEN_HEADER, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(articleDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated());
    }

    @Test
    public void update_ArticleToUpdateJson_Ok() throws Exception {

        Long articleId = 1L;
        String articleDtoJson = "{\"id\":1,\"code\":\"1236758ASM\",\"price\":12.36,\"unit\":\"KG\",\"active\":true," +
                "\"name\":\"Tomatoes\",\"description\":\"Salad Tomatoes\"}";

        this.mockMvc
                .perform(
                        put(ARTICLE_ID_PATH_VARIABLE, articleId)
                                .header(Constants.JWT.TOKEN_HEADER, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(articleDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());
    }

    @Test
    public void findAll_ArticleDtoListJson() throws Exception {

        String resultExpected = "[{\"id\":1,\"code\":\"1236758ASM\",\"price\":12.36,\"unit\":\"KG\",\"active\":true," +
                "\"name\":\"Tomatoes\",\"description\":\"Salad Tomatoes\"}]";

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(ARTICLE_PATH)
                                .header(Constants.JWT.TOKEN_HEADER, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), resultExpected);
    }

    @After
    @Rollback
    public void rollback() {

    }

}
