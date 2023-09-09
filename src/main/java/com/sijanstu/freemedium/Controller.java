package com.sijanstu.freemedium;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/medium")
    public String getMedium(@RequestParam(value = "query", defaultValue = "") String query) {
        System.out.println("https://medium.com" + query);
        return "redirect:/api/search?query=" +query;
    }
}
