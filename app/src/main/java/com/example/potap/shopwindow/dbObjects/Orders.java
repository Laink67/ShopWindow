package com.example.potap.shopwindow.dbObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "Orders")
public class Orders extends BaseObject{

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "size")
    private double size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Orders(String name, int price, String color,String image, int quantity, double size) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.image = image;
        this.quantity = quantity;
        this.size = size;
    }

    public Orders(Sneakers sneakers){
        this.name = sneakers.getName();
        this.price =sneakers.getPrice();
        this.color = sneakers.getColor();
        this.image = sneakers.getImage();
        this.quantity = 1;
    }
}
