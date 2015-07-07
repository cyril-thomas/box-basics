package com.simplyct.woddojo.helper;

import com.simplyct.woddojo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cyril on 7/5/15.
 */
@Service
public class SocialHelper {

    @Autowired
    OrganizationRepository organizationRepository;

    //@Autowired
    //Instagram instagram;

    /*public List<MediaFeedData> getInstagramFeed(Long orgId) throws InstagramException{
        Organization organization = organizationRepository.findOne(orgId);
        TagMediaFeed tagMediaFeed = instagram.getRecentMediaTags(organization.getName());
        return tagMediaFeed.getData();
    }*/
}