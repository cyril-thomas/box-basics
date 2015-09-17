package com.simplyct.woddojo.config;

import com.simplyct.woddojo.security.SecureUser;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cyril on 6/29/15.
 */
@Component
@Profile({"inmemory"})
public class LocalGymLocatorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        session.setAttribute("orgId", -1L);
        session.setAttribute("gymName", "Brickwall");

        SecurityContext securityContext = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");

        if(securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal != null) {
                    if (session.getAttribute("userName") == null) {
                        String username;
                        if (principal instanceof SecureUser) {
                            username = ((SecureUser) principal).getFirstName();
                        } else {
                            username = ((UserDetails) principal).getUsername();
                        }
                        session.setAttribute("userName", username);
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
