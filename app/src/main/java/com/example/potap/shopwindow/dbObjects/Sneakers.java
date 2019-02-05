package com.example.potap.shopwindow.dbObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "sneakers")
public class Sneakers implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "gender")              // Male = 1     Female = 0
    private int gender;

    @ColumnInfo(name = "child")
    private boolean child;

    @ColumnInfo(name = "mainImage")
    public String image;

    @ColumnInfo(name = "secondImage")
    private String secondImage;

    @ColumnInfo(name = "thirdImage")
    private String thirdImage;

    public int getId() {
        return this.id;
    }

/*
    public void setId(int id) {
        this.id = id;
    }
*/

    public Sneakers(String name, int price, String description, String color,int gender, boolean child, String image, String secondImage, String thirdImage) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.color = color;
        this.gender = gender;
        this.child = child;
        this.image = image;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
    }

    public Sneakers(String name, int price, String description, String color, int gender, boolean child, ArrayList<String> imagesLinks) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.color = color;
        this.gender = gender;
        this.child = child;
        this.image = imagesLinks.get(0);
        this.secondImage = imagesLinks.get(1);
        this.thirdImage = imagesLinks.get(2);
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

    public int getGender() {
        return this.gender;
    }

    public boolean isChild() {
        return this.child;
    }

    public String getColor() {
        return this.color;
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

    public ArrayList<String> getImagesLinks() {
        ArrayList<String> images = new ArrayList<String>();
        images.add(getImage());
        images.add(getSecondImage());
        images.add(getThirdImage());

        return images;
    }
}
