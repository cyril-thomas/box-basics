package com.simplyct.woddojo.controller;

import com.simplyct.woddojo.helper.SocialHelper;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getInstagramFeed(Model model,
                                   HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        /*try {
            List<MediaFeedData> mediaFeedDatas = socialHelper.getInstagramFeed(orgId);
            model.addAttribute("feed", mediaFeedDatas);
        } catch (InstagramException e) {
            e.printStackTrace();
            model.addAttribute("feed", null);
        }*/
        return "social/list";
    }
}
