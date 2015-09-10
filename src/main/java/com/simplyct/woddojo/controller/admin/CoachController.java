package com.simplyct.woddojo.controller.admin;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.simplyct.woddojo.helper.amazon.AwsS3Service;
import com.simplyct.woddojo.model.Coach;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.User;
import com.simplyct.woddojo.repository.CoachRepository;
import com.simplyct.woddojo.repository.OrganizationRepository;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * Created by cyril on 6/30/15.
 */
@Controller
@RequestMapping("/coach")
public class CoachController {

    @Value("${aws.bucket.name}")
    private String BUCKET_NAME;

    @Value("${aws.cdn.url}")
    private String CDN_URL;

    private final String OBJECT_NAME_TEMPLATE = "org/%s/coach/%s/%s";

    @Autowired
    CoachRepository coachRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    AwsS3Service awsS3Service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model) {
        model.addAttribute("coach", new Coach());
        return "admin/coach/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       HttpSession session,
                       @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Coach coach = coachRepository.findOne(id);
            String imageUrl = CDN_URL+coach.getProfilePic();
            model.addAttribute("coach", coach);
            model.addAttribute("imageUrl", imageUrl);
        } else {
            Long orgId = (Long) session.getAttribute("orgId");
            Organization organization = organizationRepository.findOne(orgId);
            User user = new User();
            user.setOrganization(organization);
            Coach coach = new Coach();
            coach.setUser(user);
            model.addAttribute("coach", coach);
        }
        return "admin/coach/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model,
                           HttpSession session,
                           @ModelAttribute @Valid Coach coach,
                           BindingResult result,
                           @RequestParam(value = "coach", required = false) MultipartFile file) {

        Long orgId = (Long) session.getAttribute("orgId");

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "admin/coach/edit";
        }
        if (coach.getId() == null) {

            if (StringUtils.isNotEmpty(coach.getUser().getPassword())) {
                String encryptedPassword = passwordEncoder.encode(coach.getUser().getPassword());
                coach.getUser().setPassword(encryptedPassword);
            }

            coach.getUser().setRole(User.UserRole.COACH.name());
            coachRepository.save(coach);
        }else {

            Coach dbValue = coachRepository.findOne(coach.getId());
            if (coach.getProfilePic() == null) {
                coach.setProfilePic(dbValue.getProfilePic());
            }
            if (coach.getUser().getPassword() == null) {
                coach.getUser().setPassword(dbValue.getUser().getPassword());
            }
            if (StringUtils.isNotEmpty(coach.getUser().getPassword())) {
                String encryptedPassword = passwordEncoder.encode(coach.getUser().getPassword());
                coach.getUser().setPassword(encryptedPassword);
            }
            coach.getUser().setRole(User.UserRole.COACH.name());
            coachRepository.save(coach);
        }
        if (file != null && !file.isEmpty()) {

            imageUpload(orgId, coach, file);
        }
        model.addAttribute("currentCoaches", coachRepository.findByUserOrganizationId(orgId));
        return "admin/coach/list";
    }

    private void imageUpload(Long orgId, Coach coach, MultipartFile file) {
        String fileName = file.getOriginalFilename();

        Map<String, String> userMetadata = new HashMap(1);
        userMetadata.put("db-timestamp", new DateTime().toDate().toString());
        userMetadata.put("db-filename", fileName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setUserMetadata(userMetadata);

        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        try {
            String profileUrl = awsS3Service.uploadResource(file.getInputStream(),
                                                            orgId,
                                                            coach.getId(),
                                                            ext,
                                                            file.getContentType(),
                                                            metadata,
                                                            BUCKET_NAME,
                                                            OBJECT_NAME_TEMPLATE, false);

            coach.setProfilePic(profileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        coachRepository.save(coach);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpSession session) {
        Long orgId = (Long) session.getAttribute("orgId");
        model.addAttribute("currentCoaches", coachRepository.findByUserOrganizationId(orgId));
        return "admin/coach/list";
    }

}
