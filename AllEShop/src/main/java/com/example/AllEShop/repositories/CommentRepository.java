package com.example.AllEShop.repositories;

import com.example.AllEShop.entities.Category;
import com.example.AllEShop.entities.Comment;
import com.example.AllEShop.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByItem(Item item);
}
