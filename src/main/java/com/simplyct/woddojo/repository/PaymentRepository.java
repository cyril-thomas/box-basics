package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Classes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface PaymentRepository extends CrudRepository<Classes, Long> {

    public List<Classes> findByOrganizationId(Long orgId);
}
