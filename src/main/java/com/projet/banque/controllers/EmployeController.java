package com.projet.banque.controllers;

import com.projet.banque.entities.Client;
import com.projet.banque.entities.Employe;
import com.projet.banque.services.EmployeServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeController {
    @Autowired
    private EmployeServices employeServices;

    @RequestMapping("/login")
    public String loginEmployee(Model model) {
        Employe employe = new Employe();
        model.addAttribute("employe", employe);
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("", true);
        return "login";
    }
    @GetMapping("dashboard")
    public String showDashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("employes")
    public String showEmployees(Model model) {
        List<Employe> employee = employeServices.findAllEmployee();
        model.addAttribute("employeeList", employee);

        Employe employe = new Employe();
        model.addAttribute("employee", employe);

        return "employe";
    }

//    @GetMapping("addEmployee")
//    public String addEmployee(Model model) {
//        Employe employee = new Employe();
//        model.addAttribute("employee", employee);
//        return "employeeAddView";
//    }

    @GetMapping("/updateEmployee/{code}")
    public String updateEmployee(@PathVariable(value = "code") String code, Model model) {
        Employe employee = employeServices.getEmployee(code);
        model.addAttribute("employee", employee);
        return "employeUpdate";
    }

//    @GetMapping("/updateCurrentEmployee/{code}")
//    public String updateCurrentEmployee(@PathVariable(value = "code") String code, Model model) {
//        Employe employee = employeServices.getEmployee(code);
//        model.addAttribute("employee", employee);
//        return "reglage";
//    }

    @GetMapping("/deleteEmployee/{code}")
    public String deleteEmployee(@PathVariable(value = "code") String code) {
        Employe employee = employeServices.getEmployee(code);
        employee.setStatus(0);
        employeServices.saveEmployee(employee);
        return "redirect:/employes";
    }

    @PostMapping("/saveEmployee")
    public String saveMedecin(@ModelAttribute("employee") Employe employee) {
        System.out.println(employee);
        employeServices.saveEmployee(employee);
        return "redirect:/employes";
    }
}
