package ru.psu.taskforjob.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.psu.taskforjob.dto.OrganizationDTO;
import ru.psu.taskforjob.services.OrgService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/organization")
public class OrgController {

    @Autowired
    private OrgService orgService;

    @GetMapping("getall")
    public List<Map<String, Object>> getAll() {
        return orgService.getAll();
    }

    @PostMapping("add")
    public Map<String, Object> add(@RequestParam String name, @RequestParam UUID headId) {
        return orgService.createOrg(name, headId);
    }

    @PutMapping("update")
    public Map<String, Object> update(@RequestBody Map<String, String> org){
        return orgService.updateOrg(org);
    }
}
