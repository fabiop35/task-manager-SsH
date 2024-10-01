package com.ssh.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ssh.model.Comment;
import com.ssh.repositories.CommentRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentProcessor {

    @Autowired
    private CommentRepository commentRepository;

    private Comment comment;

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return this.comment;
    }

    public void processComment(Comment comment) {
        //changing the comment attribute
        System.out.println(">>CommentProcessor.processComment()");
        comment.setText(comment.getText() + " comment processed.");
        setComment(comment);
        commentRepository.storeComment(comment);
    }

    public void validateComment(Comment comment) {
        //validating and changing the comment attribute
        System.out.println(">>ProcessComment.validateComment(): " + comment.getText());
        commentRepository.storeComment(comment);

    }
}
