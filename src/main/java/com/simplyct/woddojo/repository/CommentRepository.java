package com.simplyct.woddojo.repository;

import com.simplyct.woddojo.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyril on 9/1/15.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findByBlogId(Long blogId);
}
