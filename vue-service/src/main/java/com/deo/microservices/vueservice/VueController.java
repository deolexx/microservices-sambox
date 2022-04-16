package com.deo.microservices.vueservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userName", "Hello there");
        return "index";
    }

}
