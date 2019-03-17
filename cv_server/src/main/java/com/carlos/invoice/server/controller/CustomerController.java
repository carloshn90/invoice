package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.exception.ExceptionHandlingController;
import com.carlos.invoice.server.service.CustomerService;
import com.carlos.invoice.server.validation.ValidIdExistInDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/Customers")
public class CustomerController extends ExceptionHandlingController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private static final String CLASS = InvoiceController.class.toString();

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CustomerDto customerDto) {
        logger.info(CLASS + ": Create customer");

        this.customerService.create(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{customer-id}")
    @ValidIdExistInDto(message = "CustomerController: customer-id is not present in the customerId")
    public ResponseEntity update(@PathVariable("customer-id") Long customerId, @RequestBody CustomerDto customerDto) {
        logger.info(CLASS + ": Update customer " + customerId);

        this.customerService.update(customerDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<CustomerDto> findAll() {
        logger.info(CLASS + ": Find all customer");

        return this.customerService.findAll();
    }
}
