package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Blog;
import lombok.Data;

import java.util.Date;

/**
 * Created by cyril on 8/20/15.
 */
@Data
public class BlogPost {
    Long id;
    String title;
    String content;
    String author;
    Date date;

    public BlogPost(Blog blog){
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.author = blog.getCoach().getUser().getFirstName();
        this.content = blog.getContent();
        this.date = blog.getPostDate();
    }
}
