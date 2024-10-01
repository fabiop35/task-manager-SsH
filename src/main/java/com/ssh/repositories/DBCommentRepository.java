package com.ssh.repositories;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ssh.model.Comment;

@Repository
public class DBCommentRepository implements CommentRepository {

    @Override
    public void storeComment(Comment comment) {
        System.out.println(">>DBCommentRepository.storeComment().Storing comment: " + comment.getText());
    }
}
