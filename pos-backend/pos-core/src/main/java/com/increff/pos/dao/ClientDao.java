package com.increff.pos.dao;

import com.increff.pos.db.ClientPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientDao extends JpaRepository<ClientPojo, Integer> {

    /**
     * Executes an isolated lookup to find a client by their unique normalized name.
     */
    Optional<ClientPojo> findByName(String name);
}