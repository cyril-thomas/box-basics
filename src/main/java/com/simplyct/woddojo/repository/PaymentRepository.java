package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    public List<Payment> findByOrganizationId(Long orgId);
}
