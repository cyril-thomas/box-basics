package edu.simplyct.boxbasics.controller;

import edu.simplyct.boxbasics.model.Wod;
import edu.simplyct.boxbasics.repository.WodRepository;
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
 * Created by cyril on 6/9/15.
 */
@Controller
@RequestMapping("/wod")
public class WodController {

    @Autowired
    WodRepository wodRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String wod(Model model) {
        model.addAttribute("currentWods", wodRepository.findAll());
        return "wod/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("wod", new Wod());
        } else {
            model.addAttribute("wod", wodRepository.findOne(id));
        }
        return "wod/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           @ModelAttribute @Valid Wod wod,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "wod/edit";
        }

        if (wod.getId() == null) {
            wodRepository.save(wod);
            model.addAttribute("currentWods", wodRepository.findAll());
            return "wod/list";
        }

        Wod dbValue = wodRepository.findOne(wod.getId());
        if (dbValue.getName() == null || !dbValue.getName().equalsIgnoreCase(wod.getName())) {
            dbValue.setName(wod.getName());
        }
        if (dbValue.getDescription() == null || !dbValue.getDescription().equalsIgnoreCase(wod.getDescription())) {
            dbValue.setDescription(wod.getDescription());
        }
        if (dbValue.getNotes() == null || !dbValue.getNotes().equalsIgnoreCase(wod.getNotes())) {
            dbValue.setNotes(wod.getNotes());
        }

        wodRepository.save(dbValue);

        model.addAttribute("currentWods", wodRepository.findAll());

        return "wod/list";
    }

}
