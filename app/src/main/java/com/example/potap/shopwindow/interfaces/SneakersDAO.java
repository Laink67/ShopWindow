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

    // use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert
    void insert(Sneakers word);

    @Query("DELETE FROM sneakers")
    void deleteAll();
}
