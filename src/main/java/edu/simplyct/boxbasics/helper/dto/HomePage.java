package edu.simplyct.boxbasics.helper.dto;

import edu.simplyct.boxbasics.model.Home;
import lombok.Data;

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
