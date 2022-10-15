package com.cjcode.projectMinTic.Services;

import com.cjcode.projectMinTic.Entities.Employee;
import com.cjcode.projectMinTic.Repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UtilsService utilsService;


    @Override
    public List<Employee> getAllUsersMVC() {
        return employeeRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen Usuarios registrados");
        }
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<?> createUser(Employee employee){
        Employee employeeDb = employeeRepository.findByEmail(employee.getEmail());
        if (employeeDb == null){
            employee.setCreateAt(new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Usuario ya registrado");
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        Optional<Employee> employeeDb = employeeRepository.findById(id);
        if(employeeDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no Encontrado");
        }
        return ResponseEntity.ok(employeeDb.get());
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        Optional<Employee> employeeDb = employeeRepository.findById(id);
        if(employeeDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no Encontrado");
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Usuario Eliminado Correctamente");
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, Employee employee) {
        Optional<Employee> employeeDb = employeeRepository.findById(id);
        if(employeeDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no Encontrado");
        }
        Employee employeeEmail = employeeRepository.findByEmail(employee.getEmail());
        if(employeeEmail == null || employeeEmail.getId()==id){
            Employee employeeSave = (Employee) utilsService.validateData(employeeDb.get(),employee);
            employeeSave.setUpdateAt(new Date());
            return ResponseEntity.ok(employeeRepository.save(employeeSave));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ya registrado");
    }

    /*@Override
    public Employee updateUser(Long id, Employee employee){
        Employee employeeDb = employeeRepository.findById(id);
    }*/


}
