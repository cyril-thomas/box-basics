package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Home;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cyril on 6/29/15.
 */
@Repository
public interface HomeRepository extends CrudRepository<Home, Long> {
    public Home findByOrganizationId(Long orgId);
}
