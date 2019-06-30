package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.Application.Constants;
import com.carlos.invoice.server.dto.CustomerDto;
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
public class CustomerControllerIT {

    private final static String CUSTOMERS_PATH = "/Customers";
//    It is recommended to use spinal-case (which is highlighted by RFC3986), this case is used by Google,
//    PayPal and other big companies.
    private final static String CUSTOMER_ID_PATH_VARIABLE = "/Customers/{customer-id}";

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
    public void create_CustomerJson_Created() throws Exception {
        String customerDtoJson = "{\"name\":\"Carlos_U\",\"subName\":" +
                "\"Hernandez_U\",\"customerIdentificationDto\":{\"documentTypeEnum\":\"DNI\"," +
                "\"documentNumber\":\"4475896X_U\"}}";

        this.mockMvc
                .perform(
                        post(CUSTOMERS_PATH)
                                .header(Constants.JWT.TOKEN_HEADER, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated());
    }

    @Test
    public void update_CheckIdValidationError_BadRequest() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        String invoiceDtoJson = this.objectMapper.writeValueAsString(customerDto);
        Long customerId = 123L;

        this.mockMvc
                .perform(
                        put(CUSTOMER_ID_PATH_VARIABLE, customerId)
                                .header(Constants.JWT.TOKEN_HEADER, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void update_CustomerToUpdateJson_Ok() throws Exception {

        Long customerId = 1L;
        String customerDtoJson = "{\"id\":1,\"name\":\"Carlos\",\"subName\":" +
                "\"Hernandez\",\"customerIdentificationDto\":{\"id\":1,\"documentTypeEnum\":\"DNI\"," +
                "\"documentNumber\":\"4475896X\"}}";

        this.mockMvc
                .perform(
                        put(CUSTOMER_ID_PATH_VARIABLE, customerId)
                                .header(Constants.JWT.TOKEN_HEADER, token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());
    }

    @Test
    public void findAll_CustomerDtoListJson() throws Exception {

        String resultExpected = "[{\"id\":1,\"name\":\"Carlos_U\",\"subName\":" +
                "\"Hernandez_U\",\"customerIdentificationDto\":{\"id\":1,\"documentTypeEnum\":\"DNI\"," +
                "\"documentNumber\":\"4475896X_U\"}}]";

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(CUSTOMERS_PATH)
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
