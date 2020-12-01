package com.example.AllEShop.repositories;

import com.example.AllEShop.entities.Brand;
import com.example.AllEShop.entities.Category;
import com.example.AllEShop.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public interface ItemRepository  extends JpaRepository<Item,Long> {

    List<Item> findAllByInTopPage(Boolean inTopPage);
    List<Item> findByBrand_Name(String brand_name);

    List<Item> findByBrand_NameContainsAndNameContainsAndPriceBetweenOrderByPriceAsc(String brand_name, String name, Double price, Double price2);
    List<Item> findByBrand_NameContainsAndNameContainsAndPriceBetweenOrderByPriceDesc(String brand_name, String name, Double price, Double price2);
    List<Item> findByCategoriesContains(Category category);
}
