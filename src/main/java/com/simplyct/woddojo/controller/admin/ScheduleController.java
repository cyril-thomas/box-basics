package com.simplyct.woddojo.controller.admin;

import com.simplyct.woddojo.model.Classes;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.Schedule;
import com.simplyct.woddojo.repository.ClassesRepository;
import com.simplyct.woddojo.repository.OrganizationRepository;
import com.simplyct.woddojo.repository.ScheduleRepository;
import com.simplyct.woddojo.repository.WodRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by cyril on 6/9/15.
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    WodRepository wodRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String schedule() {
        return "admin/schedule/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            model.addAttribute("schedule", scheduleRepository.findOne(id));
        } else {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            List<Classes> classes = classesRepository.findByOrganizationId(orgId);

            Schedule schedule = new Schedule();
            Date tomorrow = new DateTime(new Date()).plusDays(1).toDate();
            schedule.setWodDate(tomorrow);
            schedule.setOrganization(organization);
            schedule.setClasses(classes);
            model.addAttribute("schedule", schedule);
        }
        model.addAttribute("workouts", wodRepository.findAll());

        return "admin/schedule/edit";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           @ModelAttribute @Valid Schedule schedule,
                           BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/schedule/edit";
        }

        Long orgId = schedule.getOrganization().getId();
        Organization organization = organizationRepository.findOne(orgId);
        schedule.setOrganization(organization);

        if (schedule.getWodDate() == null) {
            Date tomorrow = new DateTime(new Date()).plusDays(1).toDate();
            schedule.setWodDate(tomorrow);
        }

        List<Classes> classes = classesRepository.findByOrganizationId(orgId);
        schedule.setClasses(classes);
        scheduleRepository.save(schedule);
        return "admin/schedule/edit";
    }


    @RequestMapping(value = "/class/cancel", method = RequestMethod.GET)
    public String cancel(Model model,
                         @RequestParam(value = "id", required = false) Long id,
                         @RequestParam(value = "cid", required = false) Long cid) {
        Schedule schedule = scheduleRepository.findOne(id);
        if (schedule == null) {
            model.addAttribute("errors", "Please save the schedule before cancelling classes");
            return "admin/schedule/edit";
        }

        Classes classes = classesRepository.findOne(cid);

        schedule.getClasses().remove(classes);
        scheduleRepository.save(schedule);

        model.addAttribute("schedule", scheduleRepository.findOne(id));
        model.addAttribute("workouts", wodRepository.findAll());

        return "admin/schedule/edit";
    }

}
