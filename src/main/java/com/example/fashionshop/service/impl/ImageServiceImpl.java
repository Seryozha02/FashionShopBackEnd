package com.example.fashionshop.service.impl;

import com.example.fashionshop.model.Product;
import com.example.fashionshop.model.commons.Image;
import com.example.fashionshop.service.ImageService;
import com.example.fashionshop.service.ProductService;
import com.example.fashionshop.util.FileDatasource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    // TODO refactor the codes, find why cant manage content type. from server side

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public Product saveImagesToFolder(long productId, MultipartFile[] images) {
// get product by id
        Product product = productService.getById(productId);
        List<Image> imagesForDb = new LinkedList<>();
// create directory
        FileDatasource fileDatasource = new FileDatasource();
        String productFolder = fileDatasource.createProductFolder(generateFolderName(product));

// iterate for any image
        for (MultipartFile image : images) {

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            Path uploadDirectory = Paths.get(productFolder);
            String imagePath = productFolder + File.separator + fileName;
            System.out.println("imagePath\t" + imagePath);
            imagesForDb.add(new Image(imagePath));
            try (InputStream inputStream = image.getInputStream()) {
                Path filePath = uploadDirectory.resolve(fileName);

                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                try {
                    throw new IOException(" Error saving upload file" + fileName, e);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        product.getImg().addAll(imagesForDb);

        return product;
    }

    @Override
    public byte[] readAllByProductId(long productId, long imgId) throws IOException {

        Image image = null;
        for (Image item : productService.getById(productId).getImg()) {
            if (item.getId() == imgId) {
                image = item;
                break;
            }
        }
        InputStream inputStream = new FileInputStream(new File(image.getImagePath()));
        return StreamUtils.copyToByteArray(inputStream);

    }

    @Override
    public Image update(MultipartFile file) {
        return null;
    }

    @Override
    public void delete(MultipartFile file) {

    }


    private String generateFolderName(Product product) {
        return product.getName() + "_" + product.getId();
    }
}
