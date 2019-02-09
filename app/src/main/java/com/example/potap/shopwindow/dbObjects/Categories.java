package com.example.potap.shopwindow.dbObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity(tableName = "Categories")
public class Categories extends BaseObject {

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "mainImage")
    private String image;

    public Categories(String title,String image) {
        this.title = title;
        this.image = image;
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
