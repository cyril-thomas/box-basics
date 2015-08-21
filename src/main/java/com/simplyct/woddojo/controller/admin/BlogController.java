package com.simplyct.woddojo.controller.admin;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.simplyct.woddojo.helper.amazon.AwsS3Service;
import com.simplyct.woddojo.model.Blog;
import com.simplyct.woddojo.model.Coach;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.User;
import com.simplyct.woddojo.repository.BlogRepository;
import com.simplyct.woddojo.repository.CoachRepository;
import com.simplyct.woddojo.repository.OrganizationRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        if (id != null) {
            Blog blog = blogRepository.findOne(id);
            model.addAttribute("blog", blog);
        } else {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            model.addAttribute("blog", new Blog());
        }
        return "admin/blog/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           HttpSession session,
                           @ModelAttribute Blog blog) {

        Long orgId = (Long) session.getAttribute("orgId");
        blog.setCoach(coachRepository.findOne(-1L));
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
