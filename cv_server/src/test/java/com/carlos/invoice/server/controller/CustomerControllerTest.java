package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.service.CustomerService;
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
public class CustomerControllerTest {

    private final static String CUSTOMERS_PATH = "/Customers";
//    It is recommended to use spinal-case (which is highlighted by RFC3986), this case is used by Google,
//    PayPal and other big companies.
    private final static String CUSTOMER_ID_PATH_VARIABLE = "/Customers/{customer-id}";

    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private CustomerService customerService;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.customerController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void create_PostExist_Created() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        this.mockMvc
                .perform(
                        post(CUSTOMERS_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated());

    }

    @Test
    public void create_ConstraintViolationException_BadRequest() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        doThrow(new ConstraintViolationException(new HashSet<>())).when(this.customerService).create(any(CustomerDto.class));

        this.mockMvc
                .perform(
                        post(CUSTOMERS_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void update_PostExist_Created() throws Exception {

        Long customerId = 123L;
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        this.mockMvc
                .perform(
                        put(CUSTOMER_ID_PATH_VARIABLE, customerId.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());

    }

    @Test
    public void update_ConstraintViolationException_BadRequest() throws Exception {

        Long customerId = 123L;
        CustomerDto customerDto = new CustomerDto();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        doThrow(new ConstraintViolationException(new HashSet<>())).when(this.customerService).update(any(CustomerDto.class));

        this.mockMvc
                .perform(
                        put(CUSTOMER_ID_PATH_VARIABLE, customerId.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void findAll_NotResult_EmptyBodyOk() throws Exception {

        List<CustomerDto> customerDtoList = Collections.emptyList();
        TypeReference<List<CustomerDto>> customerDtoListTypeReference = new TypeReference<List<CustomerDto>>(){};

        when(this.customerService.findAll()).thenReturn(customerDtoList);

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(CUSTOMERS_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();
        List<CustomerDto> customerDtoResultList = this.objectMapper.readValue(resultJson, customerDtoListTypeReference);
        assertThat(customerDtoResultList, hasSize(0));
    }

    @Test
    public void findAll_Result_ResultInBodyOk() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(123L);
        List<CustomerDto> customerDtoList = Collections.singletonList(customerDto);
        TypeReference<List<CustomerDto>> customerDtoListTypeReference = new TypeReference<List<CustomerDto>>(){};

        when(this.customerService.findAll()).thenReturn(customerDtoList);

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(CUSTOMERS_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();
        List<CustomerDto> customerDtoResultList = this.objectMapper.readValue(resultJson, customerDtoListTypeReference);
        assertThat(customerDtoResultList, hasSize(1));
        assertEquals(customerDto.getId(), customerDtoResultList.get(0).getId());
    }
}
