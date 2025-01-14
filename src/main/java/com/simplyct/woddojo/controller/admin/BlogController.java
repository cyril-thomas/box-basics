package com.simplyct.woddojo.controller.admin;

import com.simplyct.woddojo.model.Blog;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.repository.BlogRepository;
import com.simplyct.woddojo.repository.CoachRepository;
import com.simplyct.woddojo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by cyril on 6/30/15.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CoachRepository coachRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model) {
        model.addAttribute("blog", new Blog());
        return "admin/blog/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        Long orgId = (Long) session.getAttribute("orgId");
        if (id != null) {
            Blog blog = blogRepository.findOne(id);
            model.addAttribute("blog", blog);
        } else {
            Organization organization = organizationRepository.findOne(orgId);
            Blog blog = new Blog();
            blog.setOrganization(organization);
            model.addAttribute("blog", blog);
        }
        model.addAttribute("coaches", coachRepository.findByUserOrganizationIdOrderByRankAsc(orgId));

        return "admin/blog/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           HttpSession session,
                           @ModelAttribute Blog blog) {

        Long orgId = (Long) session.getAttribute("orgId");
        blog.setOrganization(organizationRepository.findOne(orgId));
        blog.setCoach(coachRepository.findOne(blog.getCoach().getId()));
        blog.setPostDate(new Date());
        blogRepository.save(blog);

        model.addAttribute("currentBlogs", blogRepository.findByOrganizationId(orgId));
        return "admin/blog/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("currentBlogs", blogRepository.findByOrganizationId(orgId));
        return "admin/blog/list";
    }

}
