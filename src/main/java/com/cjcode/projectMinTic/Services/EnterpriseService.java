package com.cjcode.projectMinTic.Services;

import com.cjcode.projectMinTic.Entities.Enterprise;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnterpriseService {
    List<Enterprise> getAllEnterpriseMVC();
    Enterprise getEnterpriseByIdMVC(Long id);
    Enterprise createEnterpriseMVC(Enterprise enterprise);
    Enterprise updateEnterpriseMVC(Enterprise enterprise,Long id);
    Boolean deleteEnterpriseMVC(Long id);
    ResponseEntity<?> getAllEnterprises();
    ResponseEntity<?> createEnterprise(Enterprise enterprise);
    ResponseEntity<?> getEnterpriseById(Long id);
    ResponseEntity<?> deleteEnterprise(Long id);
    ResponseEntity<?> updateEnterprise(Long id,Enterprise enterprise);
}
