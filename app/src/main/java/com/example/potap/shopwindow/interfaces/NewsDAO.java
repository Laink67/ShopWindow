package com.example.potap.shopwindow.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.potap.shopwindow.dbObjects.News;

import java.util.List;

@Dao
public interface NewsDAO {

    @Query("SELECT * from news")
    LiveData<List<News>> getNews();

    @Insert
    void insert(News word);

    @Query("DELETE FROM news")
    void deleteAll();

}
