package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Home;
import lombok.Data;

import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Data
public class HomePage {
    Long   homeId;
    String landingHeader;
    String introTitle;
    String introContent;
    String servicesTitle;
    String servicesContent;
    String regTitle;
    String regContent;
    String regBanner;
    List<AnnouncementDetail> announcements;

    public HomePage() {
    }

    public HomePage(Home home) {
        this.homeId = home.getId();
        this.landingHeader = home.getTitle();
        this.introTitle = home.getIntroTitle();
        this.introContent = home.getIntroContent();
        this.servicesTitle = home.getServicesTitle();
        this.servicesContent = home.getServicesContent();
        this.regTitle = home.getRegTitle();
        this.regContent = home.getRegContent();
        this.regBanner = home.getRegBanner();
    }
}
