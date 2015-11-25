package com.simplyct.woddojo.controller.admin;

import com.simplyct.woddojo.model.Classes;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.repository.ClassesRepository;
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
 * Created by cyril on 7/14/15.
 */
@Controller
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    CoachRepository coachRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model,
                         HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");

        Organization organization = organizationRepository.findOne(orgId);
        Classes classes = new Classes();
        classes.setOrganization(organization);
        model.addAttribute("classes", classes);

        model.addAttribute("coaches", coachRepository.findByUserOrganizationIdOrderByRankAsc(orgId));
        return "admin/classes/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        Long orgId = (Long) session.getAttribute("orgId");

        if (id != null) {
            model.addAttribute("classes", classesRepository.findOne(id));
        } else {

            Organization organization = organizationRepository.findOne(orgId);
            Classes classes = new Classes();
            classes.setOrganization(organization);
            model.addAttribute("classes", classes);
        }

        model.addAttribute("coaches", coachRepository.findByUserOrganizationIdOrderByRankAsc(orgId));
        return "admin/classes/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           HttpSession session,
                           @ModelAttribute @Valid Classes classes,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/classes/edit";
        }

        Long orgId = classes.getOrganization().getId();
        Organization organization = organizationRepository.findOne(orgId);
        classes.setOrganization(organization);
        classesRepository.save(classes);

        model.addAttribute("classes", classesRepository.findByOrganizationId(orgId));
        model.addAttribute("coaches", coachRepository.findByUserOrganizationIdOrderByRankAsc(orgId));

        return "admin/classes/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("classes", classesRepository.findByOrganizationId(orgId));
        return "admin/classes/list";
    }

}
