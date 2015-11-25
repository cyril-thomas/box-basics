package com.simplyct.woddojo.helper;

import com.simplyct.woddojo.helper.dto.*;
import com.simplyct.woddojo.model.*;
import com.simplyct.woddojo.repository.*;
import org.jinstagram.Instagram;
import org.jinstagram.auth.oauth.InstagramService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cyril on 6/30/15.
 */
@Service
public class PortalHelper {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    AboutRepository aboutRepository;

    @Autowired
    CoachRepository coachRepository;

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    PricingRepository pricingRepository;

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    SocialHelper socialHelper;

    @Autowired
    InstagramService instagramService;

    @Value("${aws.cdn.url}")
    String cdnUrl;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CustomLinkRepository customLinkRepository;

    public HomePage getHomePage(Long orgId) {
        Home home = homeRepository.findByOrganizationId(orgId);
        HomePage homePage = new HomePage(home);
        homePage.setAnnouncements(getAnnouncements(orgId));
        homePage.customLinkAddAll(getCustomLinks(orgId));
        return homePage;
    }

    public GymDetail getGymDetail(Long orgId) {
        Organization organization = organizationRepository.findOne(orgId);
        return new GymDetail(organization);
    }

    public AboutPage getAboutPage(Long orgId) {
        About about = aboutRepository.findByOrganizationId(orgId);
        return new AboutPage(about);
    }

    public List<BlogPost> getBlogPosts(Long orgId){
        List<Blog> blogs = blogRepository.findByOrganizationId(orgId);
        return blogs.stream()
                    .map( e -> new BlogPost(e))
                    .collect(Collectors.toList());
    }

    public List<LinksDetail> getCustomLinks(Long orgId){
        List<CustomLink> customLinks = customLinkRepository.findByOrganizationIdOrderByLinkNameAsc(orgId);
        return customLinks.stream()
                    .map( e -> new LinksDetail(e))
                    .collect(Collectors.toList());
    }

    public BlogPost getBlogPost(Long postId){
        Blog post = blogRepository.findOne(postId);
        List<Comment> comments = commentRepository.findByBlogId(post.getId());
        List<CommentDetail> commentDetails = comments
                .stream()
                .map(e -> new CommentDetail(e))
                .collect(Collectors.toList());
        BlogPost blogPost = new BlogPost(post);
        blogPost.setComments(commentDetails);
        return blogPost;
    }

    public List<CoachDetail> getCoaches(Long orgId) {
        List<Coach> coaches = coachRepository.findByUserOrganizationIdOrderByRankAsc(orgId);
        return coaches.stream()
                      .map(e -> new CoachDetail(cdnUrl, e))
                      .collect(Collectors.toList());
    }

    public List<AnnouncementDetail> getAnnouncements(Long orgId) {
        List<Announcement> announcements = announcementRepository.findByOrganizationIdAndEndDateAfter(orgId, new Date());
        return announcements.stream()
                            .map(e -> new AnnouncementDetail(e))
                      .collect(Collectors.toList());
    }

    public WODDetail getWOD(Long orgId) {
        Schedule schedule = scheduleRepository.findByOrganizationIdAndWodDateEquals(orgId, DateTime.now().toDate());
        if(schedule != null) {
            Wod wod = schedule.getWod();
            return wod != null ? new WODDetail(wod) : null;
        }
        return null;
    }

    public List<ServiceDetail> getServices(Long orgId) {
        List<com.simplyct.woddojo.model.Service> services =
                serviceRepository.findByOrganizationId(orgId);
        return services.stream()
                       .map(e -> new ServiceDetail(e))
                       .collect(Collectors.toList());
    }


    public List<PricingDto> getPricing(Long orgId) {
        List<Pricing> pricing = pricingRepository.findByOrganizationId(orgId);
        return pricing.stream()
                      .map(e -> new PricingDto(e))
                      .collect(Collectors.toList());
    }

    public List<ClassesDto> getClasses(Long orgId) {
        List<Classes> klasses = classesRepository.findByOrganizationId(orgId);
        return klasses.stream()
                      .map(e -> new ClassesDto(e))
                      .collect(Collectors.toList());
    }
    
    public void loadHomePage(Model model, Long orgId, HttpSession httpSession){
        HomePage homePage = getHomePage(orgId);
        model.addAttribute("homeObj", homePage);

        AboutPage aboutPage = getAboutPage(orgId);
        model.addAttribute("aboutObj", aboutPage);

        List<CoachDetail> coaches = getCoaches(orgId);
        model.addAttribute("coaches", coaches);

        GymDetail gymDetail = getGymDetail(orgId);
        model.addAttribute("gymObj", gymDetail);

        WODDetail wod = getWOD(orgId);
        model.addAttribute("wod", wod);

        List<ServiceDetail> services = getServices(orgId);
        model.addAttribute("services", services);

        List<PricingDto> pricing = getPricing(orgId);
        model.addAttribute("pricing", pricing);

        List<ClassesDto> classes = getClasses(orgId);
        model.addAttribute("klasses", classes);

        if (httpSession.getAttribute("INSTAGRAM_OBJECT") == null) {
            model.addAttribute("feed", null);
            model.addAttribute("authorizationUrl", instagramService.getAuthorizationUrl(null));
        } else {

            Instagram instagram = (Instagram) httpSession.getAttribute("INSTAGRAM_OBJECT");
            model.addAttribute("feed", socialHelper.getInstagramFeed(instagram, orgId));
        }

        model.addAttribute("user", new User());
        model.addAttribute("messageUs",new MessageUs());
    }
}
