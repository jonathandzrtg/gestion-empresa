package com.cjcode.projectMinTic.Controllers;

import com.cjcode.projectMinTic.Entities.Employee;
import com.cjcode.projectMinTic.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/users")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<?> getEmployees(){
        return service.getAllUsers();
    }

    @PostMapping
    public RedirectView createEmployee(@ModelAttribute Employee employee, Model model) {
        model.addAttribute(employee);
        this.service.createUser(employee);
        return new RedirectView("/user");
    }
    /*@PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return service.createUser(employee);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long id){
        return service.getUserById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
        return service.updateUser(id,employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        return service.deleteUser(id);
    }
}
