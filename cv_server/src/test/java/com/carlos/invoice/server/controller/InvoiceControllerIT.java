package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.InvoiceDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
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
@AutoConfigureMockMvc(secure = false)
@Transactional
public class InvoiceControllerIT {

    private final static String INVOICES_PATH = "/invoices";
//    It is recommended to use spinal-case (which is highlighted by RFC3986), this case is used by Google,
//    PayPal and other big companies.
    private final static String INVOICES_ID_PATH_VARIABLE = "/invoices/{invoice-id}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void update_CheckIdValidationError_BadRequest() throws Exception {

        InvoiceDto invoiceDto = new InvoiceDto();
        String invoiceDtoJson = objectMapper.writeValueAsString(invoiceDto);
        Long invoiceId = 123L;

        this.mockMvc
                .perform(
                        put(INVOICES_ID_PATH_VARIABLE, invoiceId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void create_InvoiceToUpdateJson_Ok() throws Exception {

        String invoiceDtoJson = "{\"customerDto\":{\"name\":\"Carlos_U\",\"subName\":" +
                "\"Hernandez_U\",\"customerIdentificationDto\":{\"documentTypeEnum\":\"DNI\"," +
                "\"documentNumber\":\"4475896X_U\"}},\"creationDate\":\"2019-02-20T18:50:24.111+0000\"," +
                "\"lineItemDtoList\":[{\"numberOfItem\":2,\"code\":\"123456_U\"," +
                "\"priceItem\":7.0,\"total\":14.0}],\"total\":14.0}";

        this.mockMvc
                .perform(
                        post(INVOICES_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated());
    }

    @Test
    public void update_InvoiceToUpdateJson_Ok() throws Exception {

        Long invoiceId = 1L;
        String invoiceDtoJson = "{\"id\":1,\"customerDto\":{\"id\":1,\"name\":\"Carlos\",\"subName\":" +
                "\"Hernandez\",\"customerIdentificationDto\":{\"id\":1,\"documentTypeEnum\":\"DNI\"," +
                "\"documentNumber\":\"4475896X\"}},\"creationDate\":\"2019-02-20T18:50:24.111+0000\"," +
                "\"lineItemDtoList\":[{\"id\":1,\"numberOfItem\":1,\"code\":\"123456\"," +
                "\"priceItem\":14.0,\"total\":14.0}],\"total\":14.0}";

        this.mockMvc
                .perform(
                        put(INVOICES_ID_PATH_VARIABLE, invoiceId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());
    }

    @Test
    public void findAll_InvoiceDtoListJson() throws Exception {

        String resultExpected = "[{\"id\":1,\"customerDto\":{\"id\":1,\"name\":\"Carlos_U\",\"subName\":" +
                "\"Hernandez_U\",\"customerIdentificationDto\":{\"id\":1,\"documentTypeEnum\":\"DNI\"," +
                "\"documentNumber\":\"4475896X_U\"}},\"creationDate\":\"2019-02-20T18:50:24.111+0000\"," +
                "\"lineItemDtoList\":[{\"id\":1,\"numberOfItem\":2,\"code\":\"123456_U\"," +
                "\"priceItem\":7.0,\"total\":14.0}],\"total\":14.0}]";

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(INVOICES_PATH)
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
