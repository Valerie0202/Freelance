package org.perscholas.freelance.database.dao;

import org.perscholas.freelance.database.entity.Client;
import org.perscholas.freelance.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDAO extends JpaRepository<Client, Long> {

    Client findById(@Param("id") Integer id);

    @Query("select c from Client c where c.user.id = :userId")
    List<Client> getClients(@Param("userId") Integer userId);

}
