package com.simplyct.woddojo.controller;

import com.simplyct.woddojo.model.Schedule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "schedule/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("schedule", new Schedule());

        return "schedule/edit";
    }
}
