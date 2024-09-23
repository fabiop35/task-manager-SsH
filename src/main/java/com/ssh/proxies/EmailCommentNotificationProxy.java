package com.ssh.proxies;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ssh.model.Comment;


@Component
@Qualifier("EMAIL")
public class EmailCommentNotificationProxy implements CommentNotificationProxy {

    @Override
    public void sendComment(Comment comment) {
        System.out.println("Sending notification for comment: " + comment.getText());
    }
}
