package com.scheduler.render_schedulers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class schedulerController {

    @GetMapping("/test")
    public String testController(){
        return "Success";
    }
}
