package org.genadidharma.jjjyuk.db;

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

    @Insert(onConflict = REPLACE)
    void insertDest(Dest dest);

    @Delete
    void delete(List<Dest> dest);


}
