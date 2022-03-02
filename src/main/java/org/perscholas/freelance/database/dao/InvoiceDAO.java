package org.perscholas.freelance.database.dao;

import org.perscholas.freelance.database.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDAO extends JpaRepository<Invoice, Long> {

    Invoice findById(@Param("id") Integer id);

}
