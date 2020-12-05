package com.example.AllEShop.repositories;

import com.example.AllEShop.entities.Item;
import com.example.AllEShop.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {
    List<Picture> findByItem(Item item);
}
