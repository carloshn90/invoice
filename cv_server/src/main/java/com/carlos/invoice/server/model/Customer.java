package com.carlos.invoice.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PERSONAL_DATA")
    @SequenceGenerator(name = "SEQ_PERSONAL_DATA", sequenceName = "SEQ_PERSONAL_DATA")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CUSTOMER_IDENTIFICATION_ID", referencedColumnName = "id")
    private CustomerIdentification customerIdentification;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "customer"
    )
    private List<Invoice> invoiceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public CustomerIdentification getCustomerIdentification() {
        return customerIdentification;
    }

    public void setCustomerIdentification(CustomerIdentification customerIdentification) {
        this.customerIdentification = customerIdentification;
    }

    public void addInvoice(Invoice invoice) {

        if (this.invoiceList == null) {
            this.invoiceList = new ArrayList<>();
        }

        this.invoiceList.add(invoice);
    }
}
