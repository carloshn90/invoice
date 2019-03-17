package com.carlos.invoice.server.service;

import com.carlos.invoice.server.converter.ConverterCollection;
import com.carlos.invoice.server.dao.CustomerDao;
import com.carlos.invoice.server.dto.CustomerDto;
import com.carlos.invoice.server.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Transactional
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private static final String CLASS = CustomerService.class.toString();

    private ConversionService conversionService;
    private CustomerDao customerDao;

    @Autowired
    public CustomerService(ConversionService conversionService, CustomerDao customerDao) {
        this.conversionService = conversionService;
        this.customerDao = customerDao;
    }

    public void create(@Valid @NotNull CustomerDto customerDto) {

        logger.info(CLASS + ": create customer");

        this.save(customerDto);
    }

    public void update(@Valid @NotNull CustomerDto customerDto) {

        logger.info(CLASS + ": update customer");

        this.save(customerDto);
    }

    public List<CustomerDto> findAll() {

        logger.info(CLASS + ": find all customer");

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
