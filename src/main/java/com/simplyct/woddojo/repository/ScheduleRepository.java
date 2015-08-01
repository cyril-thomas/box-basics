package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    public Schedule findByOrganizationIdAndWodDateEquals(Long orgId, Date wodDate);

    public List<Schedule> findByOrganizationId(Long orgId);
}
