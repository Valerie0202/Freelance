package org.perscholas.freelance.database.dao;

import org.perscholas.freelance.database.entity.Invoice;
import org.perscholas.freelance.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//DAO for a whole invoice
@Repository
public interface InvoiceDAO extends JpaRepository<Invoice, Long> {

    Invoice findById(@Param("id") Integer id);

    @Query("select i from Invoice i where i.user.id = :userId")
    List<Invoice> getInvoices(@Param("userId") Integer userId);

}
