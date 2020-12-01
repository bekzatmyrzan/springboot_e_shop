package com.example.AllEShop.repositories;

import com.example.AllEShop.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
}
