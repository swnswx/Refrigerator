package com.example.refrigerator;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "fridge_items")
public class FridgeItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String expirationDate;
    private String imageUrl;

    public FridgeItem(String name, String expirationDate, String imageUrl) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getExpirationDate() { return expirationDate; }
    //public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
    public String getImageUrl() { return imageUrl; }
   //public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

}
