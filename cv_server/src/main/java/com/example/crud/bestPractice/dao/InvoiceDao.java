package com.example.crud.bestPractice.dao;

import com.example.crud.bestPractice.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InvoiceDao extends CrudRepository<Invoice, Long> {
}
