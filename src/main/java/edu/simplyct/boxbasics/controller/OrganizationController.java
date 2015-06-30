package edu.simplyct.boxbasics.controller;

import edu.simplyct.boxbasics.model.About;
import edu.simplyct.boxbasics.model.Home;
import edu.simplyct.boxbasics.model.Organization;
import edu.simplyct.boxbasics.model.Wod;
import edu.simplyct.boxbasics.repository.AboutRepository;
import edu.simplyct.boxbasics.repository.HomeRepository;
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

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    AboutRepository aboutRepository;

    @RequestMapping("/landing")
    public String getLanding() {
        return "/org/landing";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String wod(Model model) {
        model.addAttribute("currentOrgs", organizationRepository.findAll());
        return "/org/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("organization", new Organization());
            model.addAttribute("orgHome", new Home());
            model.addAttribute("orgAbout", new About());
        } else {
            Organization organization = organizationRepository.findOne(id);
            model.addAttribute("organization", organization);
            model.addAttribute("orgHome", homeRepository.findByOrganizationId(organization.getId()));
            model.addAttribute("orgAbout", aboutRepository.findByOrganizationId(organization.getId()));
        }
        return "/org/setup";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           @ModelAttribute @Valid Organization organization,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "org/setup";
        }

        model.addAttribute("message","Updated!");
        organizationRepository.save(organization);

        model.addAttribute("organization", organization);
        model.addAttribute("orgHome", homeRepository.findByOrganizationId(organization.getId()));
        model.addAttribute("orgAbout", aboutRepository.findByOrganizationId(organization.getId()));
        return "/org/setup";
    }

}
