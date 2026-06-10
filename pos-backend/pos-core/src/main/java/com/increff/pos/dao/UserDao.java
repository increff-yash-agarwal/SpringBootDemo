package com.increff.pos.dao;

import com.increff.pos.db.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserPojo, Integer> {

    // Abstract query execution for fetching unique user rows
    Optional<UserPojo> findByEmail(String email);
}