package com.cjcode.projectMinTic.Repositories;

import com.cjcode.projectMinTic.Entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Enterprise findByName(String name);
    Enterprise findByDocument(String name);
}
