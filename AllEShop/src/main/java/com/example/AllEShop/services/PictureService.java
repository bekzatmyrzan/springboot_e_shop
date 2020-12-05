package com.example.AllEShop.services;

import com.example.AllEShop.entities.Item;
import com.example.AllEShop.entities.Picture;

import java.util.List;

public interface PictureService {
    Picture addPicture(Picture picture);
    List<Picture> getAllPictures();
    Picture getPicture(Long id);
    void deletePicture(Picture picture);
    void deletePictureById(Long id);
    Picture savePicture(Picture picture);

    List<Picture> getAllPicturesByItem(Item item);
}
