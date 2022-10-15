package com.cjcode.projectMinTic.Services;

import com.cjcode.projectMinTic.Repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class FrontServiceImpl implements FrontService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String validateUser(String email, HttpSession session) {
        var employee = employeeRepository.findByEmail(email);
        if(employee == null){
            return "unauthorized";
        }
        session.setAttribute("name",employee.getName());
        if(employee.getRole().toString().equals("Admin")){
            session.setAttribute("admin",true);
        }
        else {
            session.setAttribute("admin",false);
        }
        session.setAttribute("id",employee.getId());
        session.setAttribute("enterprise",employee.getEnterprise().getId());
        session.setAttribute("enterpriseName",employee.getEnterprise().getName());
        return "index";
    }
}
