package com.example.trs.repositories;

import com.example.trs.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceJpaRepo extends JpaRepository<Invoice, Integer> {


    List<Invoice> findAllByProjectId(int id);

    @Query(value = "select * from invoices i left join projects p on i.project_id = p.projects_id" +
            " where p.company_id = :pid", nativeQuery = true)
    Invoice findInvoiceByCompanyId(@Param("pid") int id);



    Invoice findInvoiceById(int id);

}
