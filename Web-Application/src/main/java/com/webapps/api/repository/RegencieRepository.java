package com.webapps.api.repository;

import com.webapps.api.entity.Regencie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegencieRepository extends JpaRepository<Regencie, String>, JpaSpecificationExecutor<Regencie> {

    List<Regencie> getAllByProvinceId(String provinceId);
}
