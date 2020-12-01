package com.example.AllEShop.services.implementations;

import com.example.AllEShop.entities.Brand;
import com.example.AllEShop.repositories.BrandRepository;
import com.example.AllEShop.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImplementation implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(Long id) {
        return brandRepository.getOne(id);
    }

    @Override
    public void deleteBrand(Brand brand) {
        brandRepository.delete(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

}
