package com.example.groupproject.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    private int id;

    private Date timeStamp;

    @OneToOne
    private Product product;
    @OneToOne
    private User merchant;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getMerchant() {
        return merchant;
    }

    public void setMerchant(User merchant) {
        this.merchant = merchant;
    }
}
