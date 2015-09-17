package com.simplyct.woddojo.controller;

import com.simplyct.woddojo.helper.SocialHelper;
import com.simplyct.woddojo.helper.facebook.FbCommunicator;
import org.jinstagram.Instagram;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cyril on 7/5/15.
 */
@Controller
@RequestMapping("social")
public class SocialController {

    public static final String FACEBOOK_ACCESS_TOKEN = "facebookAccessToken";
    private static final String FACEBOOK_LOGGED_IN = "facebookLoggedIn";

    @Autowired
    SocialHelper socialHelper;

    @Autowired
    InstagramService instagramService;

    @Autowired
    FbCommunicator fbCommunicator;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getInstagramFeed(Model model,
                                   HttpSession session) {
        if (session.getAttribute("INSTAGRAM_OBJECT") == null) {
            model.addAttribute("feed", null);
            model.addAttribute("authorizationUrl", instagramService.getAuthorizationUrl(null));
            return "social/list";
        }

        Long orgId = (Long) session.getAttribute("orgId");

        Instagram instagram = (Instagram) session.getAttribute("INSTAGRAM_OBJECT");

        model.addAttribute("feed", socialHelper.getInstagramFeed(instagram, orgId));
        return "social/list";
    }

    @RequestMapping(value = "/facebook/isLoggedIn", method = RequestMethod.GET)
    public @ResponseBody
    boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(FACEBOOK_ACCESS_TOKEN) != null;
    }

    @RequestMapping(value = "/facebook/loginForPosting", method = RequestMethod.GET)
    public String facebookLoginForPosting(HttpServletRequest request, HttpSession session) {
        String loginUrl = fbCommunicator.getLoginUrl(getRedirectUrl(request),
                "manage_pages", "publish_pages");
        return "redirect:" + loginUrl;
    }

    @RequestMapping(value = "facebook/loginCallback", method = RequestMethod.GET, params = {"code"})
    public String loginCallback(HttpSession session, HttpServletRequest request,
                                @RequestParam(value = "code") String code) {
        String accessToken = fbCommunicator.getAccessToken(getRedirectUrl(request), code);
        session.setAttribute(FACEBOOK_ACCESS_TOKEN, accessToken);
        session.setAttribute(FACEBOOK_LOGGED_IN, true);
        String returnPage = (String) session.getAttribute("returnPage");
        if (returnPage != null) {
            session.removeAttribute("returnPage");
            return "redirect:" + returnPage;
        }
        return "social/close";
    }

    @RequestMapping(value = "facebook/loginCallback", method = RequestMethod.GET, params = {"error_message"})
    public String facebookLoginError(HttpSession session,
                                @RequestParam(value = "error_message") String errorMessage) {
        session.setAttribute("facebookErrorMessage", errorMessage);
        session.setAttribute(FACEBOOK_LOGGED_IN, false);
        return "social/facebook";
    }

    public static String getRedirectUrl(HttpServletRequest request) {
        int port = request.getServerPort();
        String serverPort = port == 80 || port == 443 ? "" : ":" + Integer.toString(port);
        return request.getScheme() + "://" + request.getServerName() +
                serverPort + request.getContextPath() +
                "/social/facebook/loginCallback";
    }

}
