package com.simplyct.woddojo.controller.admin;

import com.simplyct.woddojo.controller.SocialController;
import com.simplyct.woddojo.helper.facebook.FbCommunicator;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.Schedule;
import com.simplyct.woddojo.model.Wod;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

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
    WodRepository wodRepository;

    @Autowired
    FbCommunicator fbCommunicator;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String schedule(Model model, HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("currentSchedules", scheduleRepository.findByOrganizationId(orgId));
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

            Schedule schedule = new Schedule();
            Date tomorrow = new DateTime(new Date()).plusDays(1).toDate();
            schedule.setWodDate(tomorrow);
            schedule.setOrganization(organization);
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

        scheduleRepository.save(schedule);
        model.addAttribute("currentSchedules", scheduleRepository.findByOrganizationId(orgId));
        return "admin/schedule/list";
    }

    @RequestMapping(value = "postToFB", method = RequestMethod.GET)
    public String postToFacebook(HttpSession session, HttpServletRequest request,
                                 Model model,
                                 @RequestParam(value = "id") Long id) {
        String token = (String) session.getAttribute(SocialController.FACEBOOK_ACCESS_TOKEN);
        if (token == null) {
            String loginUrl = fbCommunicator.getLoginUrl(getRedirectUrl(request));
            session.setAttribute("returnPage", "/schedule/postToFB");
            return "redirect:" + loginUrl;
        }
        if (!fbCommunicator.hasPermissions(token, "manage_pages", "publish_pages")) {
            String getPostPermissionUrl = fbCommunicator.getLoginUrl(getRedirectUrl(request),
                    "manage_pages", "publish_pages");
            session.setAttribute("returnPage", "/schedule/postToFB");
            return "redirect:" + getPostPermissionUrl;
        }
        Map pageInfo = fbCommunicator.getPageInfo(token);
        String pageAccessToken = (String) pageInfo.get("access_token");
        String pageId = (String) pageInfo.get("id");
        Schedule schedule = scheduleRepository.findOne(id);
        Wod wod = schedule.getWod();
        String content = "WOD for " + schedule.getWodDate() + "\n" +
                wod.getName() + "\n" + wod.getDescription() + "\n" +
                wod.getNotes() + "\n" + schedule.getNotes();
        fbCommunicator.postToPage(pageId, pageAccessToken, content);
        Long orgId = schedule.getOrganization().getId();
        model.addAttribute("currentSchedules", scheduleRepository.findByOrganizationId(orgId));
        return "admin/schedule/list";
    }

    private String getRedirectUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath() +
                "/social/facebook/loginCallback";
    }
}
