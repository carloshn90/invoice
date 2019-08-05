package com.carlos.invoice.server.service;

import com.carlos.invoice.server.converter.ConverterCollection;
import com.carlos.invoice.server.dao.CustomerDao;
import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private final ConversionService conversionService;
    private final CustomerDao customerDao;

    public void create(@Valid @NotNull CustomerDto customerDto) {

        log.info("Create customer");

        this.save(customerDto);
    }

    public void update(@Valid @NotNull CustomerDto customerDto) {

        log.info("Update customer");

        this.save(customerDto);
    }

    public List<CustomerDto> findAll() {

        log.info("Find all customer");

        List<Customer> customerList = (List<Customer>) this.customerDao.findAll();

        if (customerList.isEmpty()) {
            return new ArrayList<>();
        }

        return ConverterCollection.convertList(this.conversionService, customerList, CustomerDto.class);
    }

    private void save(CustomerDto customerDto) {

        Customer customer = this.conversionService.convert(customerDto, Customer.class);

        this.customerDao.save(customer);
    }
}
