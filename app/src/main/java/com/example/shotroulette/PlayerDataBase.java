package com.example.shotroulette;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class}, version = 5, exportSchema = false)
public abstract class PlayerDataBase extends RoomDatabase {

    private static PlayerDataBase playerDataBase;
    private static String DataBaseName = "playerDataBase";

    public synchronized static PlayerDataBase getInstance(Context context) {
        if (playerDataBase == null) {
            playerDataBase= Room.databaseBuilder(context.getApplicationContext(),PlayerDataBase.class,DataBaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return playerDataBase;
    }
    public abstract PlayerDao playerDao();
}
