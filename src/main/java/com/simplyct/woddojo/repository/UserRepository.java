package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 5/25/15.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByOrganizationId(Long orgId);

    List<User> findByEmail(String email);
}
