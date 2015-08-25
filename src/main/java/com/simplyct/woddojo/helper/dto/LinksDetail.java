package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.CustomLink;
import lombok.Data;

/**
 * Created by cyril on 8/24/15.
 */
@Data
public class LinksDetail {
    String linkName;
    String linkUrl;

    public LinksDetail() {
    }

    public LinksDetail(CustomLink customLink){
        this.linkName = customLink.getLinkName();
        this.linkUrl = customLink.getLinkUrl();
    }
}
