package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Blog;
import com.simplyct.woddojo.model.Pricing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface PricingRepository extends CrudRepository<Pricing, Long> {

    public List<Pricing> findByOrganizationId(Long orgId);
}
