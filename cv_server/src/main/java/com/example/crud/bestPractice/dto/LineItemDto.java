package com.example.crud.bestPractice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LineItemDto implements IUpgradableDto {

    private Long id;

    @NotNull(message = "LineItemDto: numberOfItem is null")
    private Integer numberOfItem;

    @NotBlank(message = "LineItemDto: code is null, empty or blank")
    private String code;

    @NotNull(message = "LineItemDto: priceItem is null")
    private Double priceItem;

    @NotNull(message = "LineItemDto: total is null")
    private Double total;

    public Integer getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(Integer numberOfItem) {
        this.numberOfItem = numberOfItem;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(Double priceItem) {
        this.priceItem = priceItem;
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
