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

    public AboutPage(About about) {
        this.aboutId = about.getId();
        this.aboutHeader = about.getTitle();
        this.aboutContent = about.getAboutContent();
    }
}
