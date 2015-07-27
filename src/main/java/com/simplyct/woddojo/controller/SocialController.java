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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by cyril on 7/5/15.
 */
@Controller
@RequestMapping("social")
public class SocialController {

    private static final String FACEBOOK_ACCESS_TOKEN = "facebookAccessToken";
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

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String facebook(HttpSession session) {
        String token = (String) session.getAttribute(FACEBOOK_ACCESS_TOKEN);
        session.setAttribute(FACEBOOK_LOGGED_IN, token != null);
        return "social/facebook";
    }

    @RequestMapping(value = "/facebook/connect", method = RequestMethod.GET)
    public String facebookConnect(HttpServletRequest request, HttpSession session) {
        String token = (String) session.getAttribute(FACEBOOK_ACCESS_TOKEN);
        if (token == null) {
            String loginUrl = fbCommunicator.getLoginUrl(getRedirectUrl(request));
            return "redirect:" + loginUrl;
        }
        return "social/facebook";
    }

    @RequestMapping(value = "facebook/loginCallback", method = RequestMethod.GET, params = {"code"})
    public String facebookLogin(HttpSession session, HttpServletRequest request,
                                @RequestParam(value = "code") String code) {
        String accessToken = fbCommunicator.getAccessToken(getRedirectUrl(request), code);
        session.setAttribute(FACEBOOK_ACCESS_TOKEN, accessToken);
        session.setAttribute(FACEBOOK_LOGGED_IN, true);
        String returnPage = (String) session.getAttribute("returnPage");
        if (returnPage != null) {
            session.removeAttribute("returnPage");
            return "redirect:" + returnPage;
        }
        return "redirect:/social/facebook";
    }

    @RequestMapping(value = "facebook/loginCallback", method = RequestMethod.GET, params = {"error_message"})
    public String facebookLoginError(HttpSession session,
                                @RequestParam(value = "error_message") String errorMessage) {
        session.setAttribute("facebookErrorMessage", errorMessage);
        session.setAttribute(FACEBOOK_LOGGED_IN, false);
        return "social/facebook";
    }

    @RequestMapping(value = "facebook/post", method = RequestMethod.GET)
    public String postToFacebook(HttpSession session, HttpServletRequest request) {
        String token = (String) session.getAttribute(FACEBOOK_ACCESS_TOKEN);
        if (token == null) {
            String loginUrl = fbCommunicator.getLoginUrl(getRedirectUrl(request));
            session.setAttribute("returnPage", "/social/facebook/post");
            return "redirect:" + loginUrl;
        }
        if (!fbCommunicator.hasPermissions(token, "manage_pages", "publish_pages")) {
            String getPostPermissionUrl = fbCommunicator.getLoginUrl(getRedirectUrl(request),
                    "manage_pages", "publish_pages");
            session.setAttribute("returnPage", "/social/facebook/post");
            return "redirect:" + getPostPermissionUrl;
        }
        Map pageInfo = fbCommunicator.getPageInfo(token);
        String pageAccessToken = (String) pageInfo.get("access_token");
        String pageId = (String) pageInfo.get("id");
        fbCommunicator.postToPage(pageId, pageAccessToken, "Test post!");
        return "social/facebook";
    }

    private String getRedirectUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath() +
                "/social/facebook/loginCallback";
    }

}
