package org.genadidharma.jjjyuk.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Dest.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase database;

    private static String DATABSE_NAME = "database";
    public synchronized static AppDatabase getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,DATABSE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }
    public abstract DestDao destDao();
}
