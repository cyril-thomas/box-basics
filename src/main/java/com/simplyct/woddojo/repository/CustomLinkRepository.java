package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.CustomLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface CustomLinkRepository extends CrudRepository<CustomLink, Long> {

    public List<CustomLink> findByOrganizationId(Long orgId);

    public List<CustomLink> findByOrganizationIdOrderByLinkNameAsc(Long orgId);
}
