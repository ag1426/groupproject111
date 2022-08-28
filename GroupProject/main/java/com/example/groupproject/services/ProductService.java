package com.example.groupproject.services;

import com.example.groupproject.dao.ProductDao;
import com.example.groupproject.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProduct(){

        List<Product> productList = new ArrayList<>();
       Iterable<Product> products = productDao.findAll();
       products.forEach(e->{
           productList.add(e);
       });
       return productList;

    }

    public List<Product> getProductsOfMerchant( String id){
        return null;
    }

    public void editProduct(Product product){
        productDao.save(product);
    }
    public void deleteProduct(int productId){
        productDao.deleteById( Integer.toString(productId) );
    }

}
