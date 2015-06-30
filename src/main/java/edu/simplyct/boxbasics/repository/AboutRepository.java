package edu.simplyct.boxbasics.repository;

import edu.simplyct.boxbasics.model.About;
import edu.simplyct.boxbasics.model.Home;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cyril on 6/29/15.
 */
@Repository
public interface AboutRepository extends CrudRepository<About, Long> {
    public About findByOrganizationId(Long orgId);
}
