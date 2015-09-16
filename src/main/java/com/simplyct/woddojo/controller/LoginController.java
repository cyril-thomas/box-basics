package com.simplyct.woddojo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by molsen_admin on 8/26/15.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }
}
