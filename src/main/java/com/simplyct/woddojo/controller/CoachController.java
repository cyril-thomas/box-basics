package com.simplyct.woddojo.controller;

import com.simplyct.woddojo.model.Coach;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.User;
import com.simplyct.woddojo.repository.CoachRepository;
import com.simplyct.woddojo.repository.OrganizationRepository;
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

/**
 * Created by cyril on 6/30/15.
 */
@Controller
@RequestMapping("/coach")
public class CoachController {

    @Autowired
    CoachRepository coachRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model) {
        model.addAttribute("coach", new Coach());
        return "coach/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            model.addAttribute("coach", coachRepository.findOne(id));
        } else {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            User user = new User();
            user.setOrganization(organization);
            Coach coach = new Coach();
            coach.setUser(user);
            model.addAttribute("coach", coach);
        }
        return "coach/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           HttpSession session,
                           @ModelAttribute @Valid Coach coach,
                           BindingResult result) {

        Long orgId = (Long) session.getAttribute("orgId");

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "coach/edit";
        }

        coachRepository.save(coach);
        model.addAttribute("currentCoaches", coachRepository.findByUserOrganizationId(orgId));
        return "coach/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("currentCoaches", coachRepository.findByUserOrganizationId(orgId));
        return "coach/list";
    }

}
