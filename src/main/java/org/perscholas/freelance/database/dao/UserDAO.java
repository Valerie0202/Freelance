package org.perscholas.freelance.database.dao;

import org.perscholas.freelance.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findById(@Param("id") Integer id);
    User findByEmail(@Param("email") String email);
    User findByUsername(@Param("username") String username);


}
