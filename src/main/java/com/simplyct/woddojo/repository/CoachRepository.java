package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Coach;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface CoachRepository extends CrudRepository<Coach, Long> {

    public List<Coach> findByUserOrganizationIdOrderByRankAsc(Long orgId);

    @Query("select max(c.rank) from Coach c where c.user.organization.id = :orgId")
    public Integer findMaxRank(@Param("orgId")Long orgId);
}
