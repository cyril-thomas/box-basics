package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {

    public List<Announcement> findByOrganizationId(Long orgId);

    public List<Announcement> findByOrganizationIdAndEndDateAfter(Long orgId, Date endDate);
}
