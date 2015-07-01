package edu.simplyct.boxbasics.helper.dto;

import edu.simplyct.boxbasics.model.About;
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
