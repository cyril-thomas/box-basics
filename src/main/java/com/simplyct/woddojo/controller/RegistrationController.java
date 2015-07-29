package com.simplyct.woddojo.controller;

import com.simplyct.woddojo.helper.PortalHelper;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.User;
import com.simplyct.woddojo.repository.OrganizationRepository;
import com.simplyct.woddojo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by cyril on 7/20/15.
 */
@Controller
@RequestMapping("/reg")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String regPost(Model model,
                          HttpSession httpSession,
                          @ModelAttribute User user) {
        Long orgId = (Long) httpSession.getAttribute("orgId");

        Organization organization = organizationRepository.findOne(orgId);
        user.setOrganization(organization);
        userRepository.save(user);
        return "reg_success";
    }

}
