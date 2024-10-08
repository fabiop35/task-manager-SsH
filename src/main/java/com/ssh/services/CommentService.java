package com.ssh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ssh.aspects.ToLog;
import com.ssh.model.Comment;
import com.ssh.processors.CommentProcessor;
import com.ssh.proxies.CommentNotificationProxy;
import com.ssh.repositories.CommentRepository;
import java.util.logging.Level;

import java.util.logging.Logger;


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

    public String publishComment(Comment comment) {
        logger.info("Publish comment: " + comment.getText());
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
        return "SUCCESS";
    }

    public void sendComment(Comment c) {
        CommentProcessor commentProcessor = context.getBean(CommentProcessor.class);
        commentProcessor.setComment(c);
        commentProcessor.processComment(c);
        commentProcessor.validateComment(c);

        c = commentProcessor.getComment();
        System.out.println(">>> CommentService.sendComment().getComment: " + c.getText() + " <<<");
    }

    @ToLog
    public void deleteComment(Comment comment) {
        logger.log(Level.INFO, "Deleting comment: {0}", comment.getText());
    }
}
