package com.example.e_com_java.controller;

import com.example.e_com_java.model.Product;
import com.example.e_com_java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.RenderingResponse;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/")
public class ProductController {
    @Autowired
    private ProductService service;
//    @RequestMapping("/")
//    public String greet(){
//        return "hello world";
//    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getallproducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getproductbyid(@PathVariable int id){
        Product p=service.getproductbyid(id);
        if(p!=null){
            return new ResponseEntity<>(p,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1,HttpStatus
                    .CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("error in the controllre layer"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product/{pid}/image")
    public ResponseEntity<byte[]> getImageBypid(@PathVariable int pid){
        Product p=service.getproductbyid(pid);
        byte[] imageFile=p.getimageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(p.getimageType())).body(imageFile);
    }
//    @PutMapping("/product/{id}")
//    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product p,@RequestPart MultipartFile imageFile){
//        Product p1= null;
//        try {
//            p1 = service.updateProduct(id,p,imageFile);
//        } catch (IOException e) {
//            return  new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
//        }
//        if(p1!=null)
//            {
//                return new ResponseEntity<>("Updated", HttpStatus.OK);
//            }
//            else{
//                return  new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
//            }
//
//
//    }
@PutMapping("/product/{id}")
public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
                                            @RequestPart MultipartFile imageFile){
    Product product1 = null;
    try {
        product1 = service.updateProduct(id, product, imageFile);
    } catch (IOException e) {
        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }
    if(product1 != null)
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    else
        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
}
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteproduct(@PathVariable int id){
        Product p=service.getproductbyid(id);
        if(p!=null){
            service.deleteproduct(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        else return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
    }
//    @GetMapping("/products/search")
//    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
//        List<Product> products = service.searchProducts(keyword);
//        System.out.println("searching with " + keyword);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

}
