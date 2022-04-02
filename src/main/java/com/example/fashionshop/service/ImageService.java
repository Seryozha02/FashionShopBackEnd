package com.example.fashionshop.service;

import com.example.fashionshop.model.Product;
import com.example.fashionshop.model.commons.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {


    Product saveImagesToFolder(long productId, MultipartFile[] images);

    byte[] readAllByProductId(long productId, long imgId) throws IOException;

    Image update(MultipartFile file);

    void delete(MultipartFile file);


}
