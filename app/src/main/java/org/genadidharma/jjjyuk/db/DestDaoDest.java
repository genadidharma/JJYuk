package org.genadidharma.jjjyuk.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.genadidharma.jjjyuk.data.model.Destination;

import java.util.List;

@Dao
public interface DestDaoDest {
    @Query("SELECT * FROM destination")
    List<Destination> getAll();

    @Query("SELECT id FROM destination")
    List<String> getFavoriteIds();

    @Insert(onConflict = REPLACE)
    void insertDest(Destination dest);

    @Query("DELETE FROM destination WHERE id = :id")
    void deleteDestination(String id);
}
