package com.example.demo.HelloController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

//    @GetMapping("/")
//    public String hello(@RequestParam(name = "name") String name, Model model)
//    {
//        model.addAttribute("name", name);
//        return "index";
//    }
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}


