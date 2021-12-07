package org.genadidharma.jjjyuk.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.genadidharma.jjjyuk.data.model.Destination;

@Database(entities = {Destination.class}, version = 1, exportSchema = false)
public abstract class AppDatabaseDest extends RoomDatabase {

    private static AppDatabaseDest database;

    public synchronized static AppDatabaseDest getInstance(Context context) {
        if (database == null) {
            String DATABASE_NAME = "database";
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabaseDest.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }

    public abstract DestDaoDest destDao();
}
