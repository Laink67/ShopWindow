package com.example.potap.shopwindow.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.potap.shopwindow.dbObjects.Orders;

import java.util.List;

@Dao
public interface OrdersDAO {
    @Query("SELECT * from Orders")
    LiveData<List<Orders>> getAll();

    @Insert
    void insert(Orders word);

    @Query("DELETE FROM Orders")
    void deleteAll();

    @Query("DELETE FROM Orders WHERE id =:id")
    void delete(int id);

    @Delete
    void delete(Orders order);

    @Query("SELECT COUNT(*) FROM Orders")
    LiveData<Integer> getCount();

    @Query("SELECT SUM(price) FROM Orders")
    LiveData<Integer> getSum();
}
