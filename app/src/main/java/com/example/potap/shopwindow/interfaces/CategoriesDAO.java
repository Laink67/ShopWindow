package com.example.potap.shopwindow.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.potap.shopwindow.dbObjects.Categories;

import java.util.List;

@Dao
public interface CategoriesDAO extends BaseDAO<Categories>{

    @Query("SELECT * from Categories")
    LiveData<List<Categories>> getAll();

/*
    @Insert
    void insert(Categories word);

*/
    @Query("DELETE FROM Categories")
    void deleteAll();
}
