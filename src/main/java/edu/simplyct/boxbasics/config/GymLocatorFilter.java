package edu.simplyct.boxbasics.config;

import edu.simplyct.boxbasics.model.Organization;
import edu.simplyct.boxbasics.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by cyril on 6/29/15.
 */
@Component
public class GymLocatorFilter implements Filter{

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String serverName = servletRequest.getServerName();
        if (serverName.contains(".woddojo.com")){
            String gymName = serverName.substring(0, serverName.indexOf(".woddojo.com"));
            Organization organization = organizationRepository.findByWebDomain(gymName);
            ((HttpServletRequest)servletRequest).getSession().setAttribute("orgId",organization.getId());
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
