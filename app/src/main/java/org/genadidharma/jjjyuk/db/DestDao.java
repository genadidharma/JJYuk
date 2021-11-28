package org.genadidharma.jjjyuk.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;

@Dao
public interface DestDao {

    @Query("SELECT * FROM table_name")
    List<Dest> getAll();

    @Query("SELECT COUNT(nama_wisata) FROM table_name")
    int getRowCount();

    @Query("DELETE FROM table_name WHERE nama_wisata = :nama_wisata")
    void deleteNama(String nama_wisata);

    @Insert(onConflict = REPLACE)
    void insertDest(Dest dest);

    @Delete
    void delete(List<Dest> dest);

    @Delete
    void deleteOne(Dest dest);

}
