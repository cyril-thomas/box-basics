package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Blog;
import com.simplyct.woddojo.model.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 6/30/15.
 */
@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {

    public List<Blog> findByOrganizationId(Long orgId);
}
