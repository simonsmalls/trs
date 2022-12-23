package com.example.trs.repositories;

import com.example.trs.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceJpaRepo extends JpaRepository<Invoice, Integer> {



}
