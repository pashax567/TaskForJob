package ru.psu.taskforjob.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psu.taskforjob.dto.EmployeeDTO;
import ru.psu.taskforjob.services.EmpService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/employee")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("getall")
    public List<EmployeeDTO> getAll() {
        return empService.getAll();
    }
}
