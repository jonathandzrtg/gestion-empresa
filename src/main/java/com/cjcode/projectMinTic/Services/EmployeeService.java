package com.cjcode.projectMinTic.Services;


import com.cjcode.projectMinTic.Entities.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllUsersMVC();
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> createUser(Employee employee);
    ResponseEntity<?> getUserById(Long id);
    ResponseEntity<?> deleteUser(Long id);
    ResponseEntity<?> updateUser(Long id,Employee employee);
}
