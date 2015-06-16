package edu.simplyct.boxbasics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cyril on 6/9/15.
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String schedule() {
        return "/schedule/list";
    }
}
