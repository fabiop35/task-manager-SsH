package com.ssh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssh.model.Comment;
import com.ssh.processors.CommentProcessor;
import com.ssh.proxies.CommentNotificationProxy;
import com.ssh.repositories.CommentRepository;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentNotificationProxy commentNotificationProxy;

    private Logger logger = Logger.getLogger(CommentService.class.getName());

    @Autowired
    private ApplicationContext context;

    @Autowired
    public CommentService(CommentRepository commentRepository,
            @Qualifier("PUSH") CommentNotificationProxy commentNotificationProxy) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;

    }

    public void publishComment(Comment comment) {
        logger.info("Publish comment: " + comment.getText());
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }

    public void sendComment(Comment c) {
        CommentProcessor commentProcessor = context.getBean(CommentProcessor.class);
        commentProcessor.setComment(c);
        commentProcessor.processComment(c);
        commentProcessor.validateComment(c);

        c = commentProcessor.getComment();
        System.out.println(">>> CommentService.sendComment().getComment: " + c.getText() + " <<<");
    }
}
