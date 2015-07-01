package edu.simplyct.boxbasics.controller;

import edu.simplyct.boxbasics.helper.PortalHelper;
import edu.simplyct.boxbasics.helper.dto.GymDetail;
import edu.simplyct.boxbasics.helper.dto.HomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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
        Long orgId = (Long) httpSession.getAttribute("orgId");

        GymDetail gymDetail = portalHelper.getGymDetail(orgId);
        HomePage homePage = portalHelper.getHomePage(orgId);

        model.addAttribute("gymObj", gymDetail);
        model.addAttribute("homeObj", homePage);

        return "index";
    }

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String about(Model model, HttpSession httpSession) {
        Long orgId = (Long) httpSession.getAttribute("orgId");

        return "about";
    }

    @RequestMapping(value = "contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }

}
