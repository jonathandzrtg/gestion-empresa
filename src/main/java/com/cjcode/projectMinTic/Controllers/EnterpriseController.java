package com.cjcode.projectMinTic.Controllers;

import com.cjcode.projectMinTic.Entities.Employee;
import com.cjcode.projectMinTic.Entities.Enterprise;
import com.cjcode.projectMinTic.Services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {
    @Autowired
    private EnterpriseService service;

    @GetMapping
    public ResponseEntity<?> getAllEnterprises(){
        return service.getAllEnterprises();
    }

    @PostMapping
    public ResponseEntity<?> createEnterprise(@RequestBody Enterprise enterprise){
        return service.createEnterprise(enterprise);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnterpriseById(@PathVariable("id") Long id){
        return service.getEnterpriseById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEnterprise(@PathVariable("id") Long id,@RequestBody Enterprise enterprise){
        return service.updateEnterprise(id,enterprise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnterprise(@PathVariable("id") Long id){
        return service.deleteEnterprise(id);
    }
}
