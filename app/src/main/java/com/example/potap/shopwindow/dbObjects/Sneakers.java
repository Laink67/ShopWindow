package com.example.potap.shopwindow.dbObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "sneakers")
public class Sneakers {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name = "mainImage")
    private String image;

    @ColumnInfo(name = "secondImage")
    private String secondImage;

    @ColumnInfo(name = "thirdImage")
    private String thirdImage;

    public Sneakers(String name, int price, String description, String image,String secondImage,String thirdImage) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public String getSecondImage() {
        return this.secondImage;
    }

    public String getThirdImage() {
        return this.thirdImage;
    }

}
