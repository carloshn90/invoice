package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.exception.ExceptionHandlingController;
import com.carlos.invoice.server.service.CustomerService;
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
@RequestMapping("/Customers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController extends ExceptionHandlingController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity create(@RequestBody CustomerDto customerDto) {
        log.info("Create customer");

        this.customerService.create(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{customer-id}")
    @ValidIdExistInDto(message = "CustomerController: customer-id is not present in the customerId")
    public ResponseEntity update(@PathVariable("customer-id") Long customerId, @RequestBody CustomerDto customerDto) {
        log.info("Update customer " + customerId);

        this.customerService.update(customerDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<CustomerDto> findAll() {
        log.info("Find all customer");

        return this.customerService.findAll();
    }
}
