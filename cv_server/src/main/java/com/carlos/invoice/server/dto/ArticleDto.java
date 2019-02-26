package com.carlos.invoice.server.dto;

import com.carlos.invoice.server.enums.UnitEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ArticleDto implements IUpgradableDto {

    private Long id;

    private String code;

    @NotNull(message = "ArticleDto: price is null")
    private Double price;

    @NotNull(message = "ArticleDto: unit is null")
    private UnitEnum unit;

    @NotNull(message = "ArticleDto: active is null")
    private Boolean active;

    @NotBlank(message = "ArticleDto: name is null, empty or blank")
    private String name;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UnitEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
