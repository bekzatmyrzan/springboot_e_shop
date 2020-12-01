package com.example.AllEShop.services;

import com.example.AllEShop.entities.Brand;

import java.util.List;

public interface BrandService {
    Brand addBrand(Brand brand);
    List<Brand> getAllBrands();
    Brand getBrand(Long id);
    void deleteBrand(Brand brand);
    void deleteBrandById(Long id);
    Brand saveBrand(Brand brand);
}
