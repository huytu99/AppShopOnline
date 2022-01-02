package com.example.demosaleapp;

import java.io.Serializable;

public class Product implements Serializable {
    private String title, image,price;
    public Product(){}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product(String title, String image, String price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }
}
