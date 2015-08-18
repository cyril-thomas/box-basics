package com.simplyct.woddojo.controller.admin;

import com.simplyct.woddojo.model.About;
import com.simplyct.woddojo.model.Home;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.repository.AboutRepository;
import com.simplyct.woddojo.repository.HomeRepository;
import com.simplyct.woddojo.repository.OrganizationRepository;
import org.apache.commons.lang3.StringUtils;
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
        return "admin/org/landing";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String orgs(Model model) {
        model.addAttribute("currentOrgs", organizationRepository.findAll());
        return "admin/org/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            id = (Long) session.getAttribute("orgId");
        }
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
        return "admin/org/setup";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           @ModelAttribute @Valid Organization organization,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/org/setup";
        }

        model.addAttribute("message", "Updated!");
        organizationRepository.save(organization);

        model.addAttribute("organization", organization);
        model.addAttribute("orgHome", homeRepository.findByOrganizationId(organization.getId()));
        model.addAttribute("orgAbout", aboutRepository.findByOrganizationId(organization.getId()));
        return "admin/org/setup";
    }

    @RequestMapping(value = "/home/edit", method = RequestMethod.POST)
    public String homePost(Model model,
                           HttpSession session,
                           @ModelAttribute @Valid Home orgHome,
                           BindingResult result) {


        if (orgHome.getOrganization() == null) {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            orgHome.setOrganization(organization);
        }

        if (orgHome.getId() != null) {
            Home dbHome = homeRepository.findOne(orgHome.getId());
            if (StringUtils.isNotEmpty(orgHome.getIntroContent()) &&
                    !dbHome.getIntroContent().equalsIgnoreCase(orgHome.getIntroContent())) {
                dbHome.setIntroContent(orgHome.getIntroContent());
            }
            if (StringUtils.isNotEmpty(orgHome.getRegBanner()) &&
                    !dbHome.getRegBanner().equalsIgnoreCase(orgHome.getRegBanner())) {
                dbHome.setRegBanner(orgHome.getRegBanner());
            }
            if (StringUtils.isNotEmpty(orgHome.getRegContent()) &&
                    !dbHome.getRegContent().equalsIgnoreCase(orgHome.getRegContent())) {
                dbHome.setRegContent(orgHome.getRegContent());
            }
            if (StringUtils.isNotEmpty(orgHome.getRegTitle()) &&
                    !dbHome.getRegTitle().equalsIgnoreCase(orgHome.getRegTitle())) {
                dbHome.setRegTitle(orgHome.getRegTitle());
            }
            if (StringUtils.isNotEmpty(orgHome.getServicesTitle()) &&
                    !dbHome.getServicesTitle().equalsIgnoreCase(orgHome.getServicesTitle())) {
                dbHome.setServicesTitle(orgHome.getServicesTitle());
            }
            if (StringUtils.isNotEmpty(orgHome.getTitle()) &&
                    !dbHome.getTitle().equalsIgnoreCase(orgHome.getTitle())) {
                dbHome.setTitle(orgHome.getTitle());
            }
            if (StringUtils.isNotEmpty(orgHome.getVideoUrl()) &&
                    !dbHome.getVideoUrl().equalsIgnoreCase(orgHome.getVideoUrl())) {
                dbHome.setVideoUrl(orgHome.getVideoUrl());
            }
            if (StringUtils.isNotEmpty(orgHome.getCss()) &&
                    !dbHome.getCss().equalsIgnoreCase(orgHome.getCss())) {
                dbHome.setCss(orgHome.getCss());
            }
            model.addAttribute("message", "Updated!");
            orgHome = homeRepository.save(dbHome);

        } else {

            model.addAttribute("message", "Updated!");
            orgHome = homeRepository.save(orgHome);
        }
        model.addAttribute("organization", orgHome.getOrganization());
        model.addAttribute("orgHome", orgHome);
        model.addAttribute("orgAbout", aboutRepository.findByOrganizationId(orgHome.getOrganization().getId()));
        return "admin/org/setup";
    }

    @RequestMapping(value = "/about/edit", method = RequestMethod.POST)
    public String aboutPost(Model model,
                            HttpSession session,
                            @ModelAttribute @Valid About orgAbout,
                            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/org/setup";
        }

        if (orgAbout.getOrganization() == null) {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            orgAbout.setOrganization(organization);
        }

        model.addAttribute("message", "Updated!");
        orgAbout = aboutRepository.save(orgAbout);

        model.addAttribute("organization", orgAbout.getOrganization());
        model.addAttribute("orgHome", homeRepository.findByOrganizationId(orgAbout.getOrganization().getId()));
        model.addAttribute("orgAbout", orgAbout);
        return "admin/org/setup";
    }

}
