package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.About;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cyril on 6/29/15.
 */
@Repository
public interface AboutRepository extends CrudRepository<About, Long> {
    public About findByOrganizationId(Long orgId);
}
