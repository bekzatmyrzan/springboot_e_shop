package com.example.AllEShop.services.implementations;

import com.example.AllEShop.entities.Brand;
import com.example.AllEShop.entities.Category;
import com.example.AllEShop.entities.Item;
import com.example.AllEShop.repositories.BrandRepository;
import com.example.AllEShop.repositories.ItemRepository;
import com.example.AllEShop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemServiceImplementation implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public ArrayList<Item> getAllItemsTopPage() {
        return (ArrayList<Item>) itemRepository.findAllByInTopPage(true);
    }

    @Override
    public ArrayList<Item> getAllItems() {
        return (ArrayList<Item>) itemRepository.findAll();
    }

    @Override
    public ArrayList<Item> searchItems(String brand_name, String name, String search_price_from, String search_price_to, String orderBy) {
        Double price_from = Double.parseDouble(search_price_from);
        Double price_to = Double.parseDouble(search_price_to);
        if (orderBy.equalsIgnoreCase("ASC"))
            return (ArrayList<Item>) itemRepository.findByBrand_NameContainsAndNameContainsAndPriceBetweenOrderByPriceAsc(brand_name, name, price_from, price_to);
        else
            return (ArrayList<Item>) itemRepository.findByBrand_NameContainsAndNameContainsAndPriceBetweenOrderByPriceDesc(brand_name, name, price_from, price_to);
    }

    @Override
    public ArrayList<Item> searchItemsByBrand(String brand_name) {
        return (ArrayList<Item>) itemRepository.findByBrand_Name(brand_name);
    }

    @Override
    public ArrayList<Item> searchItemsByCategory(Category category) {
        return (ArrayList<Item>) itemRepository.findByCategoriesContains(category);
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.getOne(id);
    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
}
