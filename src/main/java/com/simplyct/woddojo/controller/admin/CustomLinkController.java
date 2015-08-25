package com.simplyct.woddojo.controller.admin;

import com.simplyct.woddojo.model.CustomLink;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.repository.CustomLinkRepository;
import com.simplyct.woddojo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by cyril on 6/30/15.
 */
@Controller
@RequestMapping("/custom")
public class CustomLinkController {
    @Autowired
    CustomLinkRepository customLinkRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model) {
        model.addAttribute("custom", new CustomLink());
        return "admin/customLinks/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            CustomLink customLink = customLinkRepository.findOne(id);
            model.addAttribute("custom", customLink);
        } else {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            CustomLink customLink = new CustomLink();
            customLink.setOrganization(organization);
            model.addAttribute("custom", customLink);
        }
        return "admin/customLinks/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           HttpSession session,
                           @ModelAttribute CustomLink customLink) {

        Long orgId = (Long) session.getAttribute("orgId");
        customLink.setOrganization(organizationRepository.findOne(orgId));
        customLinkRepository.save(customLink);

        model.addAttribute("currentLinks", customLinkRepository.findByOrganizationId(orgId));
        return "admin/customLinks/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("currentLinks", customLinkRepository.findByOrganizationId(orgId));
        return "admin/customLinks/list";
    }

}
