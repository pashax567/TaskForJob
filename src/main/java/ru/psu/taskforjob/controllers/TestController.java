package ru.psu.taskforjob.controllers;

import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.web.bind.annotation.*;
import ru.psu.taskforjob.dto.OrganizationDTO;
import ru.psu.taskforjob.jooq.tables.records.OrganizationRecord;
import ru.psu.taskforjob.services.TestService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    private int counter = 4;

    TestService testService = new TestService();

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "Monday");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Tuesday");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Wednesday");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return messages.stream().filter(x -> x.get("id").equals(id)).findFirst().get();
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PostMapping("Org")
    public void addOrg(@RequestParam String name) {
        testService.createOrg(name, null);
    }

    @GetMapping("getall")
    public List<Map<String, Object>> getAll() {

//        Result<OrganizationRecord> result = testService.getAll();
//        List<Map<String, Object>> ans = new ArrayList<Map<String, Object>>();
//
//        for (OrganizationRecord org : result) {
//            ans.add(new HashMap<String, Object>() {{
//                put("id", org.getId());
//                put("name", org.getOrgname());
//                put("headId",org.getIdheadorg());
//            }});
//        }

        return testService.getAll();
    }

    @GetMapping("getallclass")
    public List<OrganizationDTO> getallClass() {
//        Result<OrganizationRecord> result = testService.getAll();

//        List<OrganizationDTO> ans = new ArrayList<>();
//
//        for (OrganizationRecord org : result) {
//            ans.add(new OrganizationDTO(org.getId(),org.getOrgname(),org.getIdheadorg()));
//        }

        return null;
    }
}
