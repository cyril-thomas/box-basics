package com.simplyct.woddojo.helper.dto;

import com.simplyct.woddojo.model.Comment;
import lombok.Data;

import java.util.Date;

/**
 * Created by cyril on 9/1/15.
 */
@Data
public class CommentDetail {

    String content;
    String author;
    Date   date;

    public CommentDetail(Comment comment){
        this.author = comment.getUser().getFirstName();
        this.content = comment.getContent();
        this.date = comment.getPostDate();
    }
}

