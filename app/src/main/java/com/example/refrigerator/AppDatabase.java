package com.example.refrigerator;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {FridgeItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract FridgeItemDao fridgeItemDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "fridge_database")
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(() -> {
                FridgeItemDao dao = INSTANCE.fridgeItemDao();
                dao.insert(new FridgeItem("우유", "2024-06-10", "우유.png"));
                dao.insert(new FridgeItem("닭가슴살", "2024-06-30", "닭가슴살.jpg"));
                dao.insert(new FridgeItem("사과", "2024-07-15", "사과.jpg"));
            }).start();
        }
    };
}
