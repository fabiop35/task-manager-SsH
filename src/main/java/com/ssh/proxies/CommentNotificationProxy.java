package com.ssh.proxies;

import com.ssh.model.Comment;

public interface CommentNotificationProxy {

    void sendComment(Comment comment);
}
