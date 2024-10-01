package com.ssh;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssh.config.ProjectConfiguration;
import com.ssh.model.Comment;
import com.ssh.services.CommentService;

public class TaskManagerSsH {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var comment = new Comment();
        comment.setAuthor("Laurentiu");
        comment.setText("Demo comment");

        var commentService = context.getBean(CommentService.class);
        commentService.publishComment(comment);
        commentService.sendComment(comment);

        var comment2 = new Comment();
        comment2.setAuthor("Author Comment 2");
        comment2.setText("Text comment 2.");
        commentService.sendComment(comment2);

    }
}
