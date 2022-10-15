package com.cjcode.projectMinTic.services;

import com.cjcode.projectMinTic.Entities.Employee;
import com.cjcode.projectMinTic.Entities.Role;
import com.cjcode.projectMinTic.Entities.Transaction;
import com.cjcode.projectMinTic.Services.UtilsService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UtilsServicesTests {
    private UtilsService service = new UtilsService();
    @Test
    public void test(){
        Employee employeeDb = Employee.builder()
                .id(1)
                .name("test1")
                .email("test1")
                .role(Role.Admin)
                .build();
        Employee employeeBody = Employee.builder()
                .id(1)
                .name("testEdit")
                .role(Role.Operario)
                .transaction(List.of(Transaction.builder()
                                .amount(21321321)
                        .build()))
                .build();

        var employee = service.validateData(employeeDb,employeeBody);
        System.out.println(employee);
    }
}
