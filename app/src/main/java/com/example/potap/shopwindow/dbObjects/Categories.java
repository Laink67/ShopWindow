package com.example.potap.shopwindow.dbObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "Categories")
public class Categories extends BaseObject {

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "mainImage")
    private String image;

    public Categories(String title, String image) {
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
}
