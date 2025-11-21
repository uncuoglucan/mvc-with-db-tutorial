package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/modernkole")
public class modernKoleController {

    private EmployeeService employeeService;

    public modernKoleController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @RequestMapping(value = "/koleler", method = RequestMethod.GET)
    public String getKoleler(Model modelim) {

        List<Employee> koleler= employeeService.findAll();
        modelim.addAttribute("koleler", koleler);

        return "modern-kole-panel";
    }
}
