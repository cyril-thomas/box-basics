package edu.simplyct.boxbasics.controller;

import edu.simplyct.boxbasics.model.Organization;
import edu.simplyct.boxbasics.model.Wod;
import edu.simplyct.boxbasics.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by cyril on 6/9/15.
 */
@Controller
@RequestMapping("/org")
public class OrganizationController {

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String wod(Model model) {
        model.addAttribute("currentOrgs", organizationRepository.findAll());
        return "/org/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("org", new Organization());
        } else {
            model.addAttribute("org", organizationRepository.findOne(id));
        }
        return "/org/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           @ModelAttribute @Valid Wod wod,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "org/edit";
        }

        return "/org/list";
    }

}
