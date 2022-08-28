package com.example.groupproject.dao;

import com.example.groupproject.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product, String> {

    @Query(value = "SELECT * FROM Product p where  p.merchant = :id",nativeQuery = true)
    public List<Product> getProductsOfMerchant(@Param("id") String id);
}
