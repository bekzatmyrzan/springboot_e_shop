package com.example.AllEShop.services;

import com.example.AllEShop.entities.Brand;
import com.example.AllEShop.entities.Category;
import com.example.AllEShop.entities.Item;

import java.util.ArrayList;


public interface ItemService {
    Item addItem(Item item);
    ArrayList<Item> getAllItemsTopPage();
    ArrayList<Item> getAllItems();
    ArrayList<Item> searchItems(String brand_name, String name, String price_from, String price_to, String orderBy);
    ArrayList<Item> searchItemsByBrand(String brand_name);
    ArrayList<Item> searchItemsByCategory(Category category);
    Item getItem(Long id);
    void deleteItem(Item item);
    void deleteItemById(Long id);
    Item saveItem(Item item);

}
