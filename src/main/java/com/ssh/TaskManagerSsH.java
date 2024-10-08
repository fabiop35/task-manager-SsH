package com.ssh;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssh.config.ProjectConfiguration;
import com.ssh.model.Comment;
import com.ssh.services.CommentService;

public class TaskManagerSsH {

    private static Logger logger = Logger.getLogger(TaskManagerSsH.class.getName());

    public static void main(String[] args) {
        System.out.println("Hello World!");
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var comment = new Comment();
        comment.setAuthor("Laurentiu");
        comment.setText("Demo comment");

        var commentService = context.getBean(CommentService.class);
        String value = commentService.publishComment(comment);
        //commentService.sendComment(comment);
        commentService.deleteComment(comment);

        logger.info(value);
    }
}
