package com.example.AllEShop.services.implementations;

import com.example.AllEShop.entities.Brand;
import com.example.AllEShop.entities.Comment;
import com.example.AllEShop.entities.Item;
import com.example.AllEShop.repositories.BrandRepository;
import com.example.AllEShop.repositories.CommentRepository;
import com.example.AllEShop.services.BrandService;
import com.example.AllEShop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
commentRepository.deleteById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllCommentsByItem(Item item) {
        return commentRepository.findByItem(item);
    }
}
