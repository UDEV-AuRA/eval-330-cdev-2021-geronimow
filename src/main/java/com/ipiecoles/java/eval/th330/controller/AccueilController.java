package com.ipiecoles.java.eval.th330.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccueilController {

    @RequestMapping("/")
    public String accueil() {
        return "accueil";
    }
}


