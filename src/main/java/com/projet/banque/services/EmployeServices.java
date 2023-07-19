package com.projet.banque.services;

import com.projet.banque.entities.Employe;
import com.projet.banque.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeServices {
    @Autowired
    private EmployeRepository employeRepository;

    public Employe getEmployee(String code) {
        return employeRepository.getReferenceById(code);
    }

    public void saveEmployee(Employe employe){
        employeRepository.save(employe);
    }

    public List<Employe> findAllEmployee() {
        List<Employe> employeeList = employeRepository.findAll();
        List<Employe> employee = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getStatus() == 1) {
                employee.add(employeeList.get(i));
            }
        }
        return employee;
    }


}
