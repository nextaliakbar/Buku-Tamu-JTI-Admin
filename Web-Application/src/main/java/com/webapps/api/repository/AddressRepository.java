package com.webapps.api.repository;

import com.webapps.api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String>, JpaSpecificationExecutor<Address> {

    @Query("SELECT a FROM Address a WHERE a.id LIKE :prefix% ORDER BY a.id DESC LIMIT 1")
    String findLastAddressById(@Param("prefix") String prefix);
}
