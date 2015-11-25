package com.simplyct.woddojo.controller.admin;

import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.Pricing;
import com.simplyct.woddojo.repository.OrganizationRepository;
import com.simplyct.woddojo.repository.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by cyril on 6/30/15.
 */
@Controller
@RequestMapping("/pricing")
public class PricingController {

    @Autowired
    PricingRepository pricingRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model) {
        model.addAttribute("pricing", new Pricing());
        return "admin/pricing/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        Long orgId = (Long) session.getAttribute("orgId");
        if (id != null) {
            Pricing pricing = pricingRepository.findOne(id);
            model.addAttribute("pricing", pricing);
        } else {
            Organization organization = organizationRepository.findOne(orgId);
            Pricing pricing = new Pricing();
            pricing.setOrganization(organization);
            model.addAttribute("pricing", pricing);
        }

        return "admin/pricing/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           HttpSession session,
                           @ModelAttribute Pricing pricing) {

        Long orgId = (Long) session.getAttribute("orgId");
        pricing.setOrganization(organizationRepository.findOne(orgId));
        pricingRepository.save(pricing);

        model.addAttribute("currentPrices", pricingRepository.findByOrganizationId(orgId));
        return "admin/pricing/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("currentPrices", pricingRepository.findByOrganizationId(orgId));
        return "admin/pricing/list";
    }

}
