package edu.simplyct.leagueo.controller;

import edu.simplyct.leagueo.model.User;
import edu.simplyct.leagueo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by cyril on 5/25/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "users/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam(value = "id") Long id) {
        model.addAttribute("user", userRepository.findOne(id));
        return "users/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           @ModelAttribute @Valid User user,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "users/edit";
        }


        if (user.getId() == null) {
            userRepository.save(user);
            model.addAttribute("currentUsers", userRepository.findAll());
            return "users/list";
        }

        User dbValue = userRepository.findOne(user.getId());
        if (dbValue.getFirstName() == null || !dbValue.getFirstName().equalsIgnoreCase(user.getFirstName())) {
            dbValue.setFirstName(user.getFirstName());
        }
        if (dbValue.getLastName() == null || !dbValue.getLastName().equalsIgnoreCase(user.getLastName())) {
            dbValue.setLastName(user.getLastName());
        }
        if (dbValue.getEmail() == null || !dbValue.getEmail().equalsIgnoreCase(user.getEmail())) {
            dbValue.setEmail(user.getEmail());
        }
        if (dbValue.getPhone() == null || !dbValue.getPhone().equalsIgnoreCase(user.getPhone())) {
            dbValue.setPhone(user.getPhone());
        }

        userRepository.save(dbValue);

        model.addAttribute("currentUsers", userRepository.findAll());
        return "users/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("currentUsers", userRepository.findAll());
        return "users/list";
    }

}
