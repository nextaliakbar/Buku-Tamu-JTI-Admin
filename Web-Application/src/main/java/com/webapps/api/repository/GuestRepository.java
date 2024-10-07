package com.webapps.api.repository;

import com.webapps.api.entity.Address;
import com.webapps.api.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> , JpaSpecificationExecutor<Guest> {

    @Query(value = "SELECT g FROM Guest  g WHERE g.id LIKE :prefix% ORDER BY g.id DESC LIMIT 1")
    String findLastGuestById(@Param("prefix") String prefix);

    Optional<Guest> findFirstById(String id);
}
