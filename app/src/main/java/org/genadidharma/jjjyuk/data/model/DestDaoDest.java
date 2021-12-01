package org.genadidharma.jjjyuk.data.model;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DestDaoDest {
    @Query("SELECT * FROM destination")
    List<Destination> getAll();

    @Query("SELECT COUNT(nama_wisata) FROM destination")
    int getRowCount();

    @Query("DELETE FROM destination WHERE nama_wisata = :nama_wisata")
    void deleteNama(String nama_wisata);

    @Insert(onConflict = REPLACE)
    void insertDest(Destination dest);

    @Delete
    void delete(List<Destination> dest);

    @Delete
    void deleteOne(Destination dest);
}
