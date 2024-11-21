package com.ssh;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssh.config.ProjectConfiguration;
import com.ssh.model.Comment;
//import com.ssh.services.CommentService;

@SpringBootApplication
public class TaskManagerSsH {

    private static final Logger logger = Logger.getLogger(TaskManagerSsH.class.getName());

    public static void main(String[] args) {
        logger.info("Hello World!");
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var comment = new Comment();
        comment.setAuthor("Laurentiu");
        comment.setText("Demo comment");

//        var commentService = context.getBean(CommentService.class);
//        String value = commentService.publishComment(comment);
//        //commentService.sendComment(comment);
//        commentService.deleteComment(comment);
//        logger.info(value);
        SpringApplication.run(TaskManagerSsH.class, args);
    }
}
