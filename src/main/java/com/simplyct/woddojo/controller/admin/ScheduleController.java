package com.simplyct.woddojo.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.simplyct.woddojo.controller.SocialController;
import com.simplyct.woddojo.helper.facebook.FbCommunicator;
import com.simplyct.woddojo.helper.facebook.PostFormatter;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.Schedule;
import com.simplyct.woddojo.repository.OrganizationRepository;
import com.simplyct.woddojo.repository.ScheduleRepository;
import com.simplyct.woddojo.repository.WodRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;
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

    @RequestMapping(value = "/wod", method = RequestMethod.POST)
    public void getWod(
            HttpServletResponse httpServletResponse,
            @RequestParam(value = "id", required = false) Long id) {

        String workout = wodRepository.findOne(id).getDescription();

        ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        ObjectWriter OBJECT_WRITER = OBJECT_MAPPER.writer();

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(httpServletResponse);
        try {
            responseWrapper.setContentType("text/html");
            Writer out = responseWrapper.getWriter();
            OBJECT_WRITER.writeValue(out, workout);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @RequestMapping(value = "fbPageList", method = RequestMethod.GET)
    public String fbPageList(Model model, HttpSession session,
                             @RequestParam(value = "postScheduleId") Long postScheduleId) {
        String token = (String) session.getAttribute(SocialController.FACEBOOK_ACCESS_TOKEN);
        model.addAttribute("pageInfoList", fbCommunicator.getPageInfo(token));
        session.setAttribute("postScheduleId", postScheduleId);
        return "admin/schedule/fbPageList";
    }

    @RequestMapping(value = "postToFB", method = RequestMethod.POST)
    public @ResponseBody String
    postToFacebook(HttpSession session, HttpServletRequest request,
                   @RequestBody List<PostValue> pagesToPostTo) throws IOException {
        String token = (String) session.getAttribute(SocialController.FACEBOOK_ACCESS_TOKEN);
        if (token == null) {
            String loginUrl = fbCommunicator.getLoginUrl(SocialController.getRedirectUrl(request));
            session.setAttribute("returnPage", "/schedule/list");
            return "redirect:" + loginUrl;
        }
        List<Map> pageInfoList = fbCommunicator.getPageInfo(token);
        Long sessionScheduleId = (Long) session.getAttribute("postScheduleId");
        Schedule schedule = scheduleRepository.findOne(sessionScheduleId);
        for (Map pageInfo : pageInfoList) {
            String pageAccessToken = (String) pageInfo.get("access_token");
            String pageId = (String) pageInfo.get("id");
            for (PostValue postValue : pagesToPostTo) {
                if (pageId.equals(postValue.name) && "on".equals(postValue.value)) {
                    fbCommunicator.postToPage(pageId, pageAccessToken, PostFormatter.formatPost(schedule));
                }
            }
        }
        return "Post OK";
    }

    public static class PostValue {
        public String name;
        public String value;
    }
}
