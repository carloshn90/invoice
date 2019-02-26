package com.carlos.invoice.server.dto;

import com.carlos.invoice.server.enums.DocumentTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PersonalIdentificationDto implements IUpgradableDto {

    private Long id;

    @NotNull(message = "PersonalIdentificationDto: documentTypeEnum is null")
    private DocumentTypeEnum documentTypeEnum;

    @NotBlank(message = "PersonalIdentificationDto: documentNumber is null, empty or blank")
    private String documentNumber;

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

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
