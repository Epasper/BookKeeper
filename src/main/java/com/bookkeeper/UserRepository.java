package com.bookkeeper;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username) throws SQLException;

}
