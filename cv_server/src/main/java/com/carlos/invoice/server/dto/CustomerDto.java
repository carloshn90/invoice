package com.carlos.invoice.server.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerDto implements IUpgradableDto {

    private Long id;

    @NotBlank(message = "CustomerDto: name is null, empty or blank")
    private String name;

    @NotBlank(message = "CustomerDto: subName is null, empty or blank")
    private String subName;

    @NotNull(message = "CustomerDto: customerIdentificationDto is null")
    @Valid
    private CustomerIdentificationDto customerIdentificationDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public CustomerIdentificationDto getCustomerIdentificationDto() {
        return customerIdentificationDto;
    }

    public void setCustomerIdentificationDto(CustomerIdentificationDto customerIdentificationDto) {
        this.customerIdentificationDto = customerIdentificationDto;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
