package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cyril on 6/15/15.
 */
@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    public Organization findByWebDomain(String webDomain);
}
