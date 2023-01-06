package com.example.trs.repositories;

import com.example.trs.model.Activity;
import com.example.trs.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface InvoiceJpaRepo extends JpaRepository<Invoice, Integer> {


    List<Invoice> findAllByProjectId(int id);


    @Query(value = "select * from invoices i left join projects p on i.project_id = p.projects_id" +
            " where p.company_id = :pid", nativeQuery = true)
    Invoice findInvoiceByCompanyId(@Param("pid") int id);

    Invoice findInvoiceById(int id);

    Invoice findInvoiceByProjectId(int projectId);


    @Query(value = "select * from invoices i where i.invoices_id = :pid and extract(month from CAST(i.invoicedate as date)) = extract(month from CAST(:pdate as date))" +
            " and extract(year from CAST(i.invoicedate as date)) = extract(year from CAST(:pdate as date)) limit 1", nativeQuery = true)
    Invoice findInvoiceByProjectIdAndDate(@Param("pid") int projectId,@Param("pdate") LocalDate date);


    @Query(value = "select * from invoices where project_id = :pid and invoicedate between :sDate and :eDate", nativeQuery = true)
    Invoice findInvoiceByProjectIdAndStartAndEndDAndDate(@Param("pid") int projectId, @Param("sDate") LocalDate startDate, @Param("eDate") LocalDate endDate);




}
