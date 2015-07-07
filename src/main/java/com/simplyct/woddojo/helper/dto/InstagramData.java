package com.simplyct.woddojo.helper.dto;

import lombok.Data;
import org.jinstagram.entity.users.feed.MediaFeedData;

/**
 * Created by cyril on 7/6/15.
 */
@Data
public class InstagramData {
    private String thumbnailUrl;
    private String lowResolutionUrl;
    private String userName;
    private String caption;

    public InstagramData(MediaFeedData mediaFeedData) {
        this.thumbnailUrl = mediaFeedData.getImages().getThumbnail().getImageUrl();
        this.lowResolutionUrl = mediaFeedData.getImages().getLowResolution().getImageUrl();
        this.userName = mediaFeedData.getUser().getUserName();
        this.caption = mediaFeedData.getCaption().getText();
    }
}
