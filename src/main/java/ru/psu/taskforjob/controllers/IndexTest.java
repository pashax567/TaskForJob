package ru.psu.taskforjob.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexTest {

    @RequestMapping("/index")
    public String greeting(){
        return "index";
    }
}
