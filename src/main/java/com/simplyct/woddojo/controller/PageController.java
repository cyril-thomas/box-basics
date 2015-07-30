package com.simplyct.woddojo.controller;

import com.simplyct.woddojo.helper.PortalHelper;
import com.simplyct.woddojo.helper.SocialHelper;
import com.simplyct.woddojo.helper.dto.*;
import com.simplyct.woddojo.model.User;
import org.jinstagram.Instagram;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by cyril on 5/25/15.
 */
@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
    PortalHelper portalHelper;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpSession httpSession) {
        return home(model,httpSession);
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(Model model, HttpSession httpSession) {

        Long orgId = (Long) httpSession.getAttribute("orgId");

        portalHelper.loadHomePage(model, orgId, httpSession);

        model.addAttribute("user", new User());

        return "home";
    }

    @RequestMapping(value = "server_status", method = RequestMethod.GET)
    public String serverStatus() {
        return "server_status";
    }

}
