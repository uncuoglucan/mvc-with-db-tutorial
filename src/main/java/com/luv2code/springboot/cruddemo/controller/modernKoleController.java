package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/modernkole")
public class modernKoleController {

    private EmployeeService employeeService;

    public modernKoleController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/")
    public String showHome() {

        return "home";
    }


    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }



    @RequestMapping(value = "/koleler", method = RequestMethod.GET)
    public String getKoleler(Model modelim) {

        List<Employee> koleler= employeeService.findAll();
        modelim.addAttribute("koleler", koleler);

        return "modern-kole-panel";
    }

    @GetMapping("/modernKoleEkle")
    public String modernKoleEkle(Model modelimiz) {
        Employee yeniKole = new Employee();

        modelimiz.addAttribute("yeniKole", yeniKole);

        return "modern-Kole-ekle-form";
    }

    @PostMapping("/kaydet")
    public String koleKaydet(@ModelAttribute("kole") Employee kole) {

        employeeService.save(kole);

        return "redirect:/modernkole/koleler";
    }

    @GetMapping("/modernKoleGuncelleForm")
    public String modernKoleGuncelleForm(@RequestParam("koleId") int id,
                                            Model modelimiz) {
            Employee mevcutKole = employeeService.findById(id);

            modelimiz.addAttribute("yeniKole", mevcutKole);

            return "modern-Kole-ekle-form";
        }

    @GetMapping("/modernKoleGonderme")
    public String koleSil(@RequestParam("koleId") int id)
    {
        employeeService.deleteById(id);

        return "redirect:/modernkole/koleler";
    }


}
