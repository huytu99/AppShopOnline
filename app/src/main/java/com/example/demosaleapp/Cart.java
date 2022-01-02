package com.example.demosaleapp;

public class Cart {
    String title ,  image;
    int slsp ,price;
    public Cart(){};
    public Cart(String title, int price, String image, int slsp) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.slsp = slsp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSlsp() {
        return slsp;
    }

    public void setSlsp(int slsp) {
        this.slsp = slsp;
    }
}
