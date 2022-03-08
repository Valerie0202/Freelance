package org.perscholas.freelance.database.dao;

import org.perscholas.freelance.database.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDAO extends JpaRepository<Client, Long> {

    Client findById(@Param("id") Integer id);

    @Query(value = "SELECT c.* FROM client c WHERE c.user_id = :userId", nativeQuery = true)
    List<Client> getClients(@Param("userId") Integer userId);

}
