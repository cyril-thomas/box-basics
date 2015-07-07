package com.simplyct.woddojo.controller;

import com.simplyct.woddojo.helper.SocialHelper;
import org.jinstagram.Instagram;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by cyril on 7/5/15.
 */
@Controller
@RequestMapping("social")
public class SocialController {

    @Autowired
    SocialHelper socialHelper;

    @Autowired
    InstagramService instagramService;

    @RequestMapping(value = "/landing", method = RequestMethod.GET)
    public String getInstagram(Model model) {
        model.addAttribute("authorizationUrl", instagramService.getAuthorizationUrl(null));
        return "social/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getInstagramFeed(Model model,
                                   HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");

        if (session.getAttribute("INSTAGRAM_OBJECT") == null) {
            model.addAttribute("feed", null);
        }

        Instagram instagram = (Instagram) session.getAttribute("INSTAGRAM_OBJECT");

        model.addAttribute("feed", socialHelper.getInstagramFeed(instagram, orgId));
        return "social/list";
    }
}
