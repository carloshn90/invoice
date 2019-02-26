package com.carlos.invoice.server.model;

import javax.persistence.*;

@Entity
@Table(name = "LINE_ITEM")
public class LineItem {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_LINE_ITEM")
    @SequenceGenerator(name = "SEQ_LINE_ITEM", sequenceName = "SEQ_LINE_ITEM")
    private Long id;

    @Column(name = "NUMBER_OF_ITEM", nullable = false)
    private Integer numberOfItem;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "PRICE_ITEM", nullable = false)
    private Double priceItem;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
