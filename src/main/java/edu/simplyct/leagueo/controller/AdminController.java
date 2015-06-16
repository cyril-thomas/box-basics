package edu.simplyct.leagueo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cyril on 6/8/15.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/landing")
    public String getLanding(){
        return "admin/landing";
    }
}
