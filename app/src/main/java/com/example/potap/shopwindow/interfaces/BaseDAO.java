package com.example.potap.shopwindow.interfaces;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

public interface BaseDAO<T> {
    @Insert
    void insert(T word);

    @Delete
    void delete(T item);

}
