package com.carlos.invoice.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSONAL_DATA")
public class PersonalData {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PERSONAL_DATA")
    @SequenceGenerator(name = "SEQ_PERSONAL_DATA", sequenceName = "SEQ_PERSONAL_DATA")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SUB_NAME", nullable = false)
    private String subName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PERSONAL_IDENTIFICATION_ID", referencedColumnName = "id")
    private PersonalIdentification personalIdentification;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "personalData"
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public PersonalIdentification getPersonalIdentification() {
        return personalIdentification;
    }

    public void setPersonalIdentification(PersonalIdentification personalIdentification) {
        this.personalIdentification = personalIdentification;
    }

    public void addInvoice(Invoice invoice) {

        if (this.invoiceList == null) {
            this.invoiceList = new ArrayList<>();
        }

        this.invoiceList.add(invoice);
    }
}
