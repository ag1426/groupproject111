package com.example.groupproject.dao;

import com.example.groupproject.entity.Role;
import com.example.groupproject.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, String> {
}
