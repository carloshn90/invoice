package com.carlos.invoice.server.model;

import com.carlos.invoice.server.enums.DocumentTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_IDENTIFICATION")
public class CustomerIdentification {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PERSONAL_IDENTIFICATION")
    @SequenceGenerator(name = "SEQ_PERSONAL_IDENTIFICATION", sequenceName = "SEQ_PERSONAL_IDENTIFICATION")
    private Long id;

    @Column(name = "DOCUMENT_TYPE", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DocumentTypeEnum documentTypeEnum;

    @Column(name = "DOCUMENT_NUMBER", nullable = false)
    private String documentNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocumentTypeEnum getDocumentTypeEnum() {
        return documentTypeEnum;
    }

    public void setDocumentTypeEnum(DocumentTypeEnum documentTypeEnum) {
        this.documentTypeEnum = documentTypeEnum;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
