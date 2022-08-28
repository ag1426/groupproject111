package com.example.groupproject.services;

import com.example.groupproject.dao.ProductDao;
import com.example.groupproject.dao.TransactionDao;
import com.example.groupproject.entity.Product;
import com.example.groupproject.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {


    @Autowired
    private TransactionDao soldproductsDao;

    public List<Transaction> getAllSoldProducts(){

        List<Transaction> soldproductList = new ArrayList<>();
       Iterable<Transaction> transactions = soldproductsDao.findAll();
       transactions.forEach(e->{
           soldproductList.add(e);
       });
       return soldproductList;

    }
    public void addTransaction(Transaction soldproduct){
        soldproductsDao.save(soldproduct);
    }

    public void deleteProduct(long productId){
        soldproductsDao.deleteById( Long.toString(productId) );
    }

}
