package com.example.potap.shopwindow.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.potap.shopwindow.dbObjects.Sneakers;

import java.util.List;

@Dao
public interface SneakersDAO {

    @Query("SELECT * from sneakers")
    LiveData<List<Sneakers>> getSneakers();

    @Query(("SELECT * from sneakers WHERE gender = 1"))
    LiveData<List<Sneakers>> getSneakersForMale();

    @Query(("SELECT * from sneakers WHERE gender = 0"))
    LiveData<List<Sneakers>> getSneakersForFemale();

    @Query(("SELECT * from sneakers WHERE child = 'true'"))
    LiveData<List<Sneakers>> getSneakersForChildren();

    @Insert
    void insert(Sneakers word);

    @Query("SELECT * from sneakers ORDER BY price ASC")
    LiveData<List<Sneakers>> getSortedByPrice();

//    @Query("SELECT * from sneakers ORDER BY :column")
//    LiveData<List<Sneakers>> getSortedBy(String column);

    @Query("SELECT * from sneakers ORDER BY name ASC")
    LiveData<List<Sneakers>> getSortedByName();

    @Query("DELETE FROM sneakers")
    void deleteAll();
}
