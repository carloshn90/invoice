package com.carlos.invoice.server.model;

import com.carlos.invoice.server.enums.UnitEnum;

import javax.persistence.*;

@Entity
@Table(name = "ARTICLE")
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_ARTICLE")
    @SequenceGenerator(name = "SEQ_ARTICLE", sequenceName = "SEQ_ARTICLE")
    private Long id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "UNIT", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UnitEnum unit;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
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
