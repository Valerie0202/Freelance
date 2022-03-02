package org.perscholas.freelance.database.dao;

import org.perscholas.freelance.database.entity.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceLineDAO extends JpaRepository<InvoiceLine, Long> {

    InvoiceLine findById(@Param("id") Integer id);
    InvoiceLine findByInvoice(@Param("invoice") Integer invoice);

    @Query("select il from InvoiceLine il where il.invoice.id = :invoiceId")
    List<InvoiceLine> getInvoiceLines(@Param("invoiceId") Integer invoiceId);

}
