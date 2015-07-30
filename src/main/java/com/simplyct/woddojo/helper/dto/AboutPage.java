package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.About;
import lombok.Data;

/**
 * Created by cyril on 6/30/15.
 */
@Data
public class AboutPage {
    Long   aboutId;
    String aboutHeader;
    String aboutContent;
    String fbUrl;
    String twitter;
    String gPlus;

    public AboutPage(About about) {
        this.aboutId = about.getId();
        this.aboutHeader = about.getTitle();
        this.aboutContent = about.getAboutContent();
        this.fbUrl = about.getFacebookLink();
        this.twitter = about.getTwitterLink();
        this.gPlus = about.getGPlusLink();
    }
}
