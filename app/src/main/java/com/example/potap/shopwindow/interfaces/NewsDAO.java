package com.example.potap.shopwindow.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.potap.shopwindow.dbObjects.News;

import java.util.List;

@Dao
public interface NewsDAO extends BaseDAO<News>{

    @Query("SELECT * from News")
    LiveData<List<News>> getAll();

/*
    @Insert
    void insert(News word);
*/

    @Query("DELETE FROM News")
    void deleteAll();

    @Query("SELECT * from News WHERE id =:id")
    LiveData<News> getById(int id);
}
