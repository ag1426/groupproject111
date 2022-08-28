package com.example.groupproject.controller;

import com.example.groupproject.entity.Product;
import com.example.groupproject.entity.Transaction;
import com.example.groupproject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "soldproducts")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity getAllSoldProduct() {
        List<Transaction> soldProducts = transactionService.getAllSoldProducts();
        return new ResponseEntity<>(soldProducts, HttpStatus.OK);
    }

    @PostMapping("/add")
    public void createTransaction(@RequestBody Transaction soldprod) throws IOException {
       // prod.setPicByte(this.bytes);
        transactionService.addTransaction(soldprod);
       // this.bytes = null;
    }

    @DeleteMapping(path = { "/{id}" })
    public String deleteBook(@PathVariable("id") long id) {
        transactionService.deleteProduct(id);
        return "Transaction Deleted";
    }
}
