package com.simplyct.woddojo.controller.admin;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.simplyct.woddojo.helper.amazon.AwsS3Service;
import com.simplyct.woddojo.model.About;
import com.simplyct.woddojo.model.Coach;
import com.simplyct.woddojo.model.Home;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.repository.AboutRepository;
import com.simplyct.woddojo.repository.HomeRepository;
import com.simplyct.woddojo.repository.OrganizationRepository;
import org.apache.commons.lang3.StringUtils;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cyril on 6/9/15.
 */
@Controller
@RequestMapping("/org")
public class OrganizationController {

    @Value("${aws.bucket.name}")
    private String BUCKET_NAME;

    @Value("${aws.cdn.url}")
    private String CDN_URL;

    private final String LOGO_OBJECT_NAME_TEMPLATE           = "org/%s/home/logo/%s/%s";
    private final String BACKGROUND_OBJECT_NAME_TEMPLATE     = "org/%s/home/bg/%s/%s";
    private final String ALT_BACKGROUND_OBJECT_NAME_TEMPLATE = "org/%s/home/altBG/%s/%s";

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    AboutRepository aboutRepository;

    @Autowired
    AwsS3Service awsS3Service;

    @RequestMapping("/landing")
    public String getLanding() {
        return "admin/org/landing";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String orgs(Model model) {
        model.addAttribute("currentOrgs", organizationRepository.findAll());
        return "admin/org/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            id = (Long) session.getAttribute("orgId");
        }
        if (id == null) {

            model.addAttribute("organization", new Organization());
            model.addAttribute("orgHome", new Home());
            model.addAttribute("orgAbout", new About());
        } else {
            Organization organization = organizationRepository.findOne(id);
            Home home = homeRepository.findByOrganizationId(organization.getId());
            model.addAttribute("organization", organization);
            model.addAttribute("orgHome", home);
            model.addAttribute("orgAbout", aboutRepository.findByOrganizationId(organization.getId()));

            if(home.getLogoUrl()!= null && !home.getLogoUrl().isEmpty()) {
                String logoUrl = CDN_URL + home.getLogoUrl();
                model.addAttribute("logoUrl", logoUrl);
            }

            if(home.getBgUrl()!= null && !home.getBgUrl().isEmpty()) {
                String mainBgUrl = CDN_URL + home.getBgUrl();
                model.addAttribute("mainBgUrl", mainBgUrl);
            }

            if(home.getAltBgUrl()!= null && !home.getAltBgUrl().isEmpty()) {
                String bgAltUrl = CDN_URL + home.getAltBgUrl();
                model.addAttribute("bgAltUrl", bgAltUrl);
            }
        }
        return "admin/org/setup";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           @ModelAttribute @Valid Organization organization,
                           BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/org/setup";
        }

        model.addAttribute("message", "Updated!");
        organizationRepository.save(organization);

        model.addAttribute("organization", organization);
        model.addAttribute("orgHome", homeRepository.findByOrganizationId(organization.getId()));
        model.addAttribute("orgAbout", aboutRepository.findByOrganizationId(organization.getId()));
        return "admin/org/setup";
    }

    @RequestMapping(value = "/home/edit", method = RequestMethod.POST)
    public String homePost(Model model,
                           HttpSession session,
                           @ModelAttribute Home orgHome,
                           @RequestParam(value = "logo", required = false) MultipartFile logo,
                           @RequestParam(value = "mainBg", required = false) MultipartFile background,
                           @RequestParam(value = "bgAlt", required = false) MultipartFile altBackground
    ) {


        if (orgHome.getOrganization() == null) {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            orgHome.setOrganization(organization);
        }

        if (orgHome.getId() != null) {
            Home dbHome = homeRepository.findOne(orgHome.getId());
            if (StringUtils.isNotEmpty(orgHome.getIntroContent()) &&
                    !dbHome.getIntroContent().equalsIgnoreCase(orgHome.getIntroContent())) {
                dbHome.setIntroContent(orgHome.getIntroContent());
            }
            if (StringUtils.isNotEmpty(orgHome.getRegBanner()) &&
                    !dbHome.getRegBanner().equalsIgnoreCase(orgHome.getRegBanner())) {
                dbHome.setRegBanner(orgHome.getRegBanner());
            }
            if (StringUtils.isNotEmpty(orgHome.getRegContent()) &&
                    !dbHome.getRegContent().equalsIgnoreCase(orgHome.getRegContent())) {
                dbHome.setRegContent(orgHome.getRegContent());
            }
            if (StringUtils.isNotEmpty(orgHome.getRegTitle()) &&
                    !dbHome.getRegTitle().equalsIgnoreCase(orgHome.getRegTitle())) {
                dbHome.setRegTitle(orgHome.getRegTitle());
            }
            if (StringUtils.isNotEmpty(orgHome.getServicesTitle()) &&
                    !dbHome.getServicesTitle().equalsIgnoreCase(orgHome.getServicesTitle())) {
                dbHome.setServicesTitle(orgHome.getServicesTitle());
            }
            if (StringUtils.isNotEmpty(orgHome.getTitle()) &&
                    !dbHome.getTitle().equalsIgnoreCase(orgHome.getTitle())) {
                dbHome.setTitle(orgHome.getTitle());
            }
            if (StringUtils.isNotEmpty(orgHome.getVideoUrl()) &&
                    !dbHome.getVideoUrl().equalsIgnoreCase(orgHome.getVideoUrl())) {
                dbHome.setVideoUrl(orgHome.getVideoUrl());
            }
            if (StringUtils.isNotEmpty(orgHome.getCss()) &&
                    !dbHome.getCss().equalsIgnoreCase(orgHome.getCss())) {
                dbHome.setCss(orgHome.getCss());
            }
            model.addAttribute("message", "Updated!");
            orgHome = homeRepository.save(dbHome);

        } else {

            model.addAttribute("message", "Updated!");
            orgHome = homeRepository.save(orgHome);
        }

        if (logo != null && !logo.isEmpty()) {
            imageUpload(orgHome.getOrganization().getId(), orgHome, logo, ImageType.LOGO);
        }
        if (background != null && !background.isEmpty()) {
            imageUpload(orgHome.getOrganization().getId(), orgHome, background, ImageType.BG);
        }
        if (altBackground != null && !altBackground.isEmpty()) {
            imageUpload(orgHome.getOrganization().getId(), orgHome, altBackground, ImageType.ALT_BG);
        }


        model.addAttribute("organization", orgHome.getOrganization());
        model.addAttribute("orgHome", orgHome);
        model.addAttribute("orgAbout", aboutRepository.findByOrganizationId(orgHome.getOrganization().getId()));
        return "admin/org/setup";
    }

    @RequestMapping(value = "/about/edit", method = RequestMethod.POST)
    public String aboutPost(Model model,
                            HttpSession session,
                            @ModelAttribute @Valid About orgAbout,
                            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/org/setup";
        }

        if (orgAbout.getOrganization() == null) {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            orgAbout.setOrganization(organization);
        }

        model.addAttribute("message", "Updated!");
        orgAbout = aboutRepository.save(orgAbout);

        model.addAttribute("organization", orgAbout.getOrganization());
        model.addAttribute("orgHome", homeRepository.findByOrganizationId(orgAbout.getOrganization().getId()));
        model.addAttribute("orgAbout", orgAbout);
        return "admin/org/setup";
    }

    private void imageUpload(Long orgId, Home home, MultipartFile file, ImageType imageType) {
        String fileName = file.getOriginalFilename();

        Map<String, String> userMetadata = new HashMap(1);
        userMetadata.put("db-timestamp", new DateTime().toDate().toString());
        userMetadata.put("db-filename", fileName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setUserMetadata(userMetadata);

        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        String templateName = StringUtils.EMPTY;

        switch (imageType) {
            case LOGO: templateName = LOGO_OBJECT_NAME_TEMPLATE;
                break;
            case BG: templateName = BACKGROUND_OBJECT_NAME_TEMPLATE;
                break;
            case ALT_BG: templateName = ALT_BACKGROUND_OBJECT_NAME_TEMPLATE;
                break;
        }

        try {
            String imageUrl = awsS3Service.uploadResource(file.getInputStream(),
                                                            orgId,
                                                            home.getId(),
                                                            ext,
                                                            file.getContentType(),
                                                            metadata,
                                                            BUCKET_NAME,
                                                            templateName, false);

            switch (imageType) {
                case LOGO: home.setLogoUrl(imageUrl);
                    break;
                case BG: home.setBgUrl(imageUrl);
                    break;
                case ALT_BG: home.setAltBgUrl(imageUrl);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        homeRepository.save(home);
    }


    private enum ImageType {
        LOGO, BG, ALT_BG
    }


}
