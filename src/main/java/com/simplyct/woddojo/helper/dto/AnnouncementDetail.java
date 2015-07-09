package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Announcement;
import lombok.Data;

/**
 * Created by cyril on 7/5/15.
 */
@Data
public class AnnouncementDetail {
    Long aId;
    String content;
    String title;

    public AnnouncementDetail(Announcement announcement) {
        this.aId = announcement.getId();
        this.title = announcement.getTitle();
        this.content = announcement.getContent();
    }
}
