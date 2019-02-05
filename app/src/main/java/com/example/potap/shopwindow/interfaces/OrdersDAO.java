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
    @Query("SELECT * from orders")
    LiveData<List<Orders>> getOrders();

    @Insert
    void insert(Orders word);

    @Query("DELETE FROM orders")
    void deleteAll();

    @Delete
    void delete(Orders order);

    @Query("SELECT COUNT(*) FROM orders")
    LiveData<Integer> getCount();

    @Query("SELECT SUM(price) FROM orders")
    LiveData<Integer> getSum();
}
