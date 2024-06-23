package com.example.refrigerator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FridgeItemDao {
    @Query("SELECT * FROM fridge_items")
    List<FridgeItem> getAll();

    @Insert
    void insert(FridgeItem item);

    @Update
    void update(FridgeItem item);

    @Delete
    void delete(FridgeItem item);
}
