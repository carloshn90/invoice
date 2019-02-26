package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.service.InvoiceService;
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

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceControllerTest {

    private final static String INVOICES_PATH = "/invoices";
//    It is recommended to use spinal-case (which is highlighted by RFC3986), this case is used by Google,
//    PayPal and other big companies.
    private final static String INVOICES_ID_PATH_VARIABLE = "/invoices/{invoice-id}";

    @InjectMocks
    private InvoiceController invoiceController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private InvoiceService invoiceService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.invoiceController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void create_PostExist_Created() throws Exception {

        InvoiceDto invoiceDto = new InvoiceDto();
        String invoiceDtoJson = objectMapper.writeValueAsString(invoiceDto);

        this.mockMvc
                .perform(
                        post(INVOICES_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invoiceDtoJson)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated());

    }

    @Test
    public void create_ConstraintViolationException_BadRequest() throws Exception {

        InvoiceDto invoiceDto = new InvoiceDto();
        String invoiceDtoJson = objectMapper.writeValueAsString(invoiceDto);

        doThrow(new ConstraintViolationException(new HashSet<>())).when(invoiceService).create(any(InvoiceDto.class));

        this.mockMvc
                .perform(
                        post(INVOICES_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void update_ConstraintViolationException_BadRequest() throws Exception {

        InvoiceDto invoiceDto = new InvoiceDto();
        String invoiceDtoJson = objectMapper.writeValueAsString(invoiceDto);
        String invoiceId = "123";

        doThrow(new ConstraintViolationException(new HashSet<>())).when(invoiceService).update(any(InvoiceDto.class));

        this.mockMvc
                .perform(
                        put(INVOICES_ID_PATH_VARIABLE, invoiceId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void update_IllegalArgumentException_BadRequest() throws Exception {

        Long invoiceId = 123L;
        InvoiceDto invoiceDto = new InvoiceDto();
        String invoiceDtoJson = objectMapper.writeValueAsString(invoiceDto);

        doThrow(new IllegalArgumentException()).when(invoiceService).update(any(InvoiceDto.class));

        this.mockMvc
                .perform(
                        put(INVOICES_ID_PATH_VARIABLE, invoiceId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

    }

    @Test
    public void update_PutExist_Ok() throws Exception {

        Long invoiceId = 123L;
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoiceId);
        String invoiceDtoJson = objectMapper.writeValueAsString(invoiceDto);

        this.mockMvc
                .perform(
                        put(INVOICES_ID_PATH_VARIABLE, invoiceId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invoiceDtoJson)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());

    }

    @Test
    public void find_NotResult_EmptyBodyOk() throws Exception {

        List<InvoiceDto> invoiceDtoList = Collections.emptyList();
        TypeReference<List<InvoiceDto>> invoiceDtoListTypeReference = new TypeReference<List<InvoiceDto>>(){};

        when(this.invoiceService.find()).thenReturn(invoiceDtoList);

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(INVOICES_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();
        List<InvoiceDto> invoiceDtoResultList = this.objectMapper.readValue(resultJson, invoiceDtoListTypeReference);
        assertThat(invoiceDtoResultList, hasSize(0));
    }

    @Test
    public void find_Result_ResultInBodyOk() throws Exception {

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(123L);
        List<InvoiceDto> invoiceDtoList = Collections.singletonList(invoiceDto);
        TypeReference<List<InvoiceDto>> invoiceDtoListTypeReference = new TypeReference<List<InvoiceDto>>(){};

        when(this.invoiceService.find()).thenReturn(invoiceDtoList);

        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(INVOICES_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();
        List<InvoiceDto> invoiceDtoResultList = this.objectMapper.readValue(resultJson, invoiceDtoListTypeReference);
        assertThat(invoiceDtoResultList, hasSize(1));
        assertEquals(invoiceDtoResultList.get(0).getId(), invoiceDto.getId());
    }

}
