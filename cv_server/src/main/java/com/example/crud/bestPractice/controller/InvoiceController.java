package com.example.crud.bestPractice.controller;

import com.example.crud.bestPractice.dto.InvoiceDto;
import com.example.crud.bestPractice.exception.ExceptionHandlingController;
import com.example.crud.bestPractice.service.InvoiceService;
import com.example.crud.bestPractice.validation.ValidIdExistInDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController("/invoices")
public class InvoiceController extends ExceptionHandlingController {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    private static final String CLASS = InvoiceController.class.toString();

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody InvoiceDto invoiceDto) {
        logger.info(CLASS + ": Create invoice");

        this.invoiceService.create(invoiceDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/invoices/{invoice-id}")
    @ValidIdExistInDto(message = "InvoiceController: invoice-id is not present in the invoiceDto")
    public ResponseEntity update(@PathVariable("invoice-id") Long invoiceId, @RequestBody InvoiceDto invoiceDto) {
        logger.info(CLASS + ": Update invoice " + invoiceId);

        this.invoiceService.update(invoiceDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<InvoiceDto> find() {
        logger.info(CLASS + ": Find all invoices");

        return this.invoiceService.find();
    }
}
