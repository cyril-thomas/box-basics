package edu.simplyct.boxbasics.repository;

import edu.simplyct.boxbasics.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cyril on 6/15/15.
 */
@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    public Organization findByWebDomain(String webDomain);
}
