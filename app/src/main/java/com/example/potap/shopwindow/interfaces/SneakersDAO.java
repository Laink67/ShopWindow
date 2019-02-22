package com.example.potap.shopwindow.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.potap.shopwindow.dbObjects.Sneakers;

import java.util.List;

@Dao
public interface SneakersDAO extends BaseDAO<Sneakers> {

    @Query("SELECT * from Sneakers")
    LiveData<List<Sneakers>> getAll();

    @Query("SELECT * FROM Sneakers WHERE newsId = :id")
    LiveData<List<Sneakers>> getByNewsId(int id);

    @Query("SELECT * FROM Sneakers WHERE categoriesId = :id")
    LiveData<List<Sneakers>> getByCategoriesId(int id);

    //SORT
    @Query("SELECT * FROM Sneakers WHERE categoriesId =:id ORDER BY name")
    LiveData<List<Sneakers>> getSortedWithCategoriesIdByName(int id);

    @Query("SELECT * FROM Sneakers WHERE categoriesId =:id ORDER BY price")
    LiveData<List<Sneakers>> getSortedWithCategoriesIdByPrice(int id);

    @Query("SELECT * FROM Sneakers WHERE newsId =:id ORDER BY price")
    LiveData<List<Sneakers>> getSortedWithNewsIdByPrice(int id);

    @Query("SELECT * FROM Sneakers WHERE newsId =:id ORDER BY name")
    LiveData<List<Sneakers>> getSortedWithNewsIdByName(int id);

    @Query("SELECT * from Sneakers ORDER BY price ASC")
    LiveData<List<Sneakers>> getSortedByPrice();

    @Query("SELECT * from Sneakers WHERE gender=:gender AND child=:child AND sport = -1 ORDER BY price")
    LiveData<List<Sneakers>> getSortedByPrice(int gender, boolean child);

    @Query("SELECT * from Sneakers WHERE gender=:gender AND child=:child AND sport = -1 ORDER BY name")
    LiveData<List<Sneakers>> getSortedByName(int gender, boolean child);

    @Query("SELECT * FROM Sneakers WHERE child = :child ORDER BY name")
    LiveData<List<Sneakers>> getSortedChildrenByName(boolean child);

    @Query("SELECT * FROM Sneakers WHERE child = :child ORDER BY price")
    LiveData<List<Sneakers>> getSortedChildrenByPrice(boolean child);

    @Query("SELECT * from Sneakers ORDER BY name ASC")
    LiveData<List<Sneakers>> getSortedByName();
    
    //
    @Query("DELETE FROM Sneakers")
    void deleteAll();

    @Query("SELECT * FROM Sneakers WHERE name LIKE :searchString")
    LiveData<List<Sneakers>> search(String searchString);
}
