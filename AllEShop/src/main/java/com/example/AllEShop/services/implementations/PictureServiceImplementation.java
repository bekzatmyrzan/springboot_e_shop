package com.example.AllEShop.services.implementations;

import com.example.AllEShop.entities.Brand;
import com.example.AllEShop.entities.Item;
import com.example.AllEShop.entities.Picture;
import com.example.AllEShop.repositories.BrandRepository;
import com.example.AllEShop.repositories.PictureRepository;
import com.example.AllEShop.services.BrandService;
import com.example.AllEShop.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImplementation implements PictureService {
    @Autowired
    private PictureRepository pictureRepository;


    @Override
    public Picture addPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public Picture getPicture(Long id) {
        return pictureRepository.getOne(id);
    }

    @Override
    public void deletePicture(Picture picture) {
        pictureRepository.delete(picture);
    }

    @Override
    public void deletePictureById(Long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    public Picture savePicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public List<Picture> getAllPicturesByItem(Item item) {
        return pictureRepository.findByItem(item);
    }
}
