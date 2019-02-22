package com.example.potap.shopwindow.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.potap.shopwindow.dbObjects.Sizes;

import java.util.List;

@Dao
public interface SizesDAO extends BaseDAO<Sizes>{

    @Query("SELECT * FROM Sizes")
    LiveData<List<Sizes>> getAll();

    @Query("SELECT * FROM Sizes WHERE sneakersId = :sneakersId")
    LiveData<List<Sizes>> getSizesBySneakersId(int sneakersId);

/*
    @Insert
    void insert(Sizes size);
*/

    @Query("DELETE FROM sneakers")
    void deleteAll();

    @Query("DELETE FROM Sizes WHERE id = :id")
    void deleteById(int id);
}
