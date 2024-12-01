package com.example.e_com_java.service;

import com.example.e_com_java.model.Product;
import com.example.e_com_java.repo.ProductRepo;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;
    public  List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getproductbyid(int id) {
        System.out.println(repo.findById(id));
        return repo.findById(id).orElse(new Product());
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

//    public Product updateProduct(int id, Product p, MultipartFile imageFile) throws IOException {
//        p.setImageData(imageFile.getBytes());
//        p.setImageName(imageFile.getOriginalFilename());
//        p.setImageType(imageFile.getContentType());
//        return repo.save(p);
//    }
public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
    product.setImageData(imageFile.getBytes());
    product.setImageName(imageFile.getOriginalFilename());
    product.setImageType(imageFile.getContentType());
    return repo.save(product);
}

    public void deleteproduct(int id) {
         repo.deleteById(id);
    }
//    public List<Product> searchProducts(String keyword) {
//        return repo.se
//    }
}
