package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.InvoiceDto;
import com.carlos.invoice.server.exception.ExceptionHandlingController;
import com.carlos.invoice.server.service.InvoiceService;
import com.carlos.invoice.server.validation.ValidIdExistInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@Slf4j
@RequestMapping("/invoices")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceController extends ExceptionHandlingController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity create(@RequestBody InvoiceDto invoiceDto) {
        log.info("Create invoice");

        this.invoiceService.create(invoiceDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{invoice-id}")
    @ValidIdExistInDto(message = "InvoiceController: invoice-id is not present in the invoiceDto")
    public ResponseEntity update(@PathVariable("invoice-id") Long invoiceId, @RequestBody InvoiceDto invoiceDto) {
        log.info("Update invoice " + invoiceId);

        this.invoiceService.update(invoiceDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<InvoiceDto> findAll() {
        log.info("Find all invoices");

        return this.invoiceService.findAll();
    }
}
