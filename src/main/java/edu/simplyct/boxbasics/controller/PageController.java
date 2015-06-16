package edu.simplyct.boxbasics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cyril on 5/25/15.
 */
@Controller
@RequestMapping("/")
public class PageController {


    @RequestMapping(method= RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("greeting", "Hello Cyril Springloaded!!");
        return "index";
    }

    @RequestMapping(value = "about", method= RequestMethod.GET)
    public String about() {
        return "about";
    }

    @RequestMapping(value = "contact", method= RequestMethod.GET)
    public String contact() {
        return "contact";
    }

}
