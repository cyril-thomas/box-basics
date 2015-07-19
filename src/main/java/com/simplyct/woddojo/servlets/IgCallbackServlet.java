package com.simplyct.woddojo.servlets;

import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cyril on 7/6/15.
 */
@Component
public class IgCallbackServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String code = request.getParameter("code");
        Verifier verifier = new Verifier(code);

        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        InstagramService instagramService = ctx.getBean("instagramService", InstagramService.class);

        Token accessToken = instagramService.getAccessToken(null, verifier);
        Instagram instagram = new Instagram(accessToken);

        HttpSession session = request.getSession();
        session.setAttribute("INSTAGRAM_OBJECT", instagram);

        if (!(Boolean) session.getAttribute("home_flow")) {
            response.sendRedirect(request.getContextPath() + "/social/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
