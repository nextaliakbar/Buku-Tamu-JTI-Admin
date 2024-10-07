package com.webapps.api.repository;

import com.webapps.api.entity.Need;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedRepository extends JpaRepository<Need, String>, JpaSpecificationExecutor<Need> {

    @Query("SELECT n FROM Need n WHERE n.id LIKE :prefix% ORDER BY n.id DESC LIMIT 1")
    String findLastNeedById(@Param("prefix") String prefix);
}
