package com.example.potap.shopwindow.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.potap.shopwindow.dbObjects.Sneakers;

import java.util.List;

@Dao
public interface SneakersDAO {

    @Query("SELECT * from Sneakers")
    LiveData<List<Sneakers>> getAll();

    @Query("SELECT * from Sneakers WHERE gender = 1 AND child = :child AND sport = -1")
    LiveData<List<Sneakers>> getSneakersForMale(boolean child);

    @Query("SELECT * from Sneakers WHERE gender = 0 AND child = :child AND sport = -1")
    LiveData<List<Sneakers>> getSneakersForFemale(boolean child);

    @Query("SELECT * from Sneakers WHERE child = :child")
    LiveData<List<Sneakers>> getSneakersForChildren(boolean child);

    @Query("SELECT * FROM Sneakers WHERE sport = :sport")
    LiveData<List<Sneakers>> getSportSneakers(int sport);

    @Insert
    void insert(Sneakers word);

    //SORT
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

    @Query("SELECT * FROM Sneakers WHERE sport = :sport ORDER BY name")
    LiveData<List<Sneakers>> getSportSortedByName(int sport);

    @Query("SELECT * FROM Sneakers WHERE sport = :sport ORDER BY price")
    LiveData<List<Sneakers>> getSportSortedByPrice(int sport);
//
    @Query("DELETE FROM Sneakers")
    void deleteAll();

    @Query("SELECT * FROM Sneakers WHERE name LIKE :searchString")
    LiveData<List<Sneakers>> search(String searchString);
}
