package com.ssh.repositories;

import com.ssh.model.Comment;

public interface CommentRepository {

    void storeComment(Comment comment);
}
