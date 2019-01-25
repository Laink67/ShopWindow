package com.example.potap.shopwindow.dbObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "news")
public class News implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "mainImage")
    public String image;

    public News(String title,String image) {
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return this.image;
    }

    public ArrayList<String> getImagesLinks(){
        ArrayList<String> images = new ArrayList<String>();
        images.add(getImage());
        return images;
    }

}