package com.simplyct.woddojo.helper;

import com.simplyct.woddojo.helper.dto.*;
import com.simplyct.woddojo.model.*;
import com.simplyct.woddojo.repository.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ScheduleRepository scheduleRepository;


    public HomePage getHomePage(Long orgId) {
        Home home = homeRepository.findByOrganizationId(orgId);
        HomePage homePage = new HomePage(home);
        homePage.setAnnouncements(getAnnouncements(orgId));
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

    public List<CoachDetail> getCoaches(Long orgId) {
        List<Coach> coaches = coachRepository.findByUserOrganizationId(orgId);
        return coaches.stream()
                      .map(e -> new CoachDetail(e))
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
        return new WODDetail(schedule.getWod());
    }

    public List<ServiceDetail> getServices(Long orgId) {
        List<com.simplyct.woddojo.model.Service> services =
                serviceRepository.findByOrganizationId(orgId);
        return services.stream()
                       .map(e -> new ServiceDetail(e))
                       .collect(Collectors.toList());
    }
}
