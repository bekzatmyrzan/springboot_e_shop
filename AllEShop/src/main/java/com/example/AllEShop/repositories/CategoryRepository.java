package com.example.AllEShop.repositories;

import com.example.AllEShop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category getByNameEquals(String name);
}
