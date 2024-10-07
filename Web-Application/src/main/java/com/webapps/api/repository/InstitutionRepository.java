package com.webapps.api.repository;

import com.webapps.api.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, String>, JpaSpecificationExecutor<Institution> {

    @Query("SELECT i FROM Institution i WHERE i.id LIKE :prefix% ORDER BY i.id DESC LIMIT 1")
    String findLastInstitutionByd(@Param("prefix") String prefix);
}
