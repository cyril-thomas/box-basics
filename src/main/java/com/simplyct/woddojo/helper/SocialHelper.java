package com.simplyct.woddojo.helper;

import com.simplyct.woddojo.helper.dto.InstagramData;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.repository.OrganizationRepository;
import org.jinstagram.Instagram;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cyril on 7/5/15.
 */
@Service
public class SocialHelper {

    @Autowired
    OrganizationRepository organizationRepository;

    public List<InstagramData> getInstagramFeed(Instagram instagram, Long orgId) {
        try {

            Organization organization = organizationRepository.findOne(orgId);
            TagMediaFeed tagMediaFeed = instagram.getRecentMediaTags(organization.getHashTag());
            List<MediaFeedData> mediaFeedData = tagMediaFeed.getData();
            return mediaFeedData
                    .stream()
                    .map(e -> new InstagramData(e))
                    .collect(Collectors.toList());
        } catch (InstagramException e) {
            e.printStackTrace();
            return null;
        }
    }
}