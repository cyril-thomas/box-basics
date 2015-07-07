package com.simplyct.woddojo.controller;

import com.simplyct.woddojo.model.Announcement;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.repository.AnnouncementRepository;
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
import java.util.Calendar;

/**
 * Created by cyril on 6/30/15.
 */
@Controller
@RequestMapping("/announcement")
public class AnnoucementController {

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model) {
        model.addAttribute("anno", new Announcement());
        return "announcement/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            model.addAttribute("anno", announcementRepository.findOne(id));
        } else {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            Announcement announcement = new Announcement();
            announcement.setOrganization(organization);
            model.addAttribute("anno", announcement);
        }
        return "announcement/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           HttpSession session,
                           @ModelAttribute @Valid Announcement announcement,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "announcement/edit";
        }

        Long orgId = announcement.getOrganization().getId();
        Organization organization = organizationRepository.findOne(orgId);
        announcement.setOrganization(organization);

        if (announcement.getEndDate() == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 7);
            announcement.setEndDate(calendar.getTime());
        }

        announcementRepository.save(announcement);
        model.addAttribute("announcements", announcementRepository.findByOrganizationId(orgId));
        return "announcement/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("announcements", announcementRepository.findByOrganizationId(orgId));
        return "announcement/list";
    }

}
