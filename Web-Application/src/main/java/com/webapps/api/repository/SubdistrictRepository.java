package com.webapps.api.repository;

import com.webapps.api.entity.Subdistrict;
import com.webapps.api.model.SubdistrictResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubdistrictRepository extends JpaRepository<Subdistrict, String>, JpaSpecificationExecutor<Subdistrict> {

    List<Subdistrict> getAllByRegencieId(String regencieId);
}
