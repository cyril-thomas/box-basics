package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {

    public List<Service> findByOrganizationId(Long orgId);
}
