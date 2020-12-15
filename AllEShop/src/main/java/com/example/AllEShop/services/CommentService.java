package com.example.AllEShop.services;

import com.example.AllEShop.entities.Comment;
import com.example.AllEShop.entities.Item;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> getAllComments();
    Comment getComment(Long id);
    void deleteComment(Comment comment);
    void deleteCommentById(Long id);
    Comment saveComment(Comment comment);

    List<Comment> getAllCommentsByItem(Item item);
}
