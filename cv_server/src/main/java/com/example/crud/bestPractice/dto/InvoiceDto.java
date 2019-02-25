package com.example.crud.bestPractice.dto;

import com.example.crud.bestPractice.validation.ValidInvoiceDto;
import com.example.crud.bestPractice.validation.ValidLineItemDtoList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ValidInvoiceDto(message = "InvoiceDto: invalid invoiceDto")
public class InvoiceDto implements IUpgradableDto {

    private Long id;

    @NotNull(message = "InvoiceDto: PersonalDto is null")
    @Valid
    private PersonalDataDto personalDataDto;

    private Date creationDate;

    @Valid
    @ValidLineItemDtoList(message = "InvoiceDto: Invalid LineItemDto")
    private List<LineItemDto> lineItemDtoList;

    @NotNull(message = "InvoiceDto: Total is null")
    private Double total;

    public InvoiceDto() {
    }

    public PersonalDataDto getPersonalDataDto() {
        return personalDataDto;
    }

    public void setPersonalDataDto(PersonalDataDto personalDataDto) {
        this.personalDataDto = personalDataDto;
    }

    public List<LineItemDto> getLineItemDtoList() {
        return lineItemDtoList;
    }

    public void setLineItemDtoList(List<LineItemDto> lineItemDtoList) {
        this.lineItemDtoList = lineItemDtoList;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
