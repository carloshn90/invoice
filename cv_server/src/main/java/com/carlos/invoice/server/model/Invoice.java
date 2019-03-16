package com.carlos.invoice.server.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "INVOICE")
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_INVOICE")
    @SequenceGenerator(name = "SEQ_INVOICE", sequenceName = "SEQ_INVOICE")
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "invoice",
            orphanRemoval = true
    )
    private List<LineItem> lineItemList;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
