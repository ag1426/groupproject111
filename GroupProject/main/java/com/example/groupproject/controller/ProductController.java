package com.example.groupproject.controller;

import com.example.groupproject.dao.ProductDao;
import com.example.groupproject.entity.Product;
import com.example.groupproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //path below is localhost:9090/products/all/
    @GetMapping("/all")
    public ResponseEntity getAllProduct() {
        List<Product> products = productService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    // to find all products related to a merchant
    @RequestMapping(value = "/merchant/{id}", method = RequestMethod.GET)
    public ResponseEntity getProductsOfMerchant(@PathVariable String id) {
        List<Product> products = productService.getProductsOfMerchant(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product) {
       productService.editProduct(product);
    }

    @PostMapping("/add")
    public void createProduct(@RequestBody Product prod) throws IOException {
       // prod.setPicByte(this.bytes);
        productService.editProduct(prod);
       // this.bytes = null;
    }
    @DeleteMapping( "/list/{id}" )
    public String deleteBook(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Deleted";
    }
}
