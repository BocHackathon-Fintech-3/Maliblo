package com.maliblo.fincam.Db.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.maliblo.fincam.Db.tables.ExtractedData;

import java.util.List;

@Dao
public interface ExtractedDataDao {
    @Insert
    void insert(ExtractedData data);

    @Update
    void update(ExtractedData data);

    @Delete
    void delete(ExtractedData data);

    @Query("SELECT * FROM ExtractedData ORDER BY id ASC")
    LiveData<List<ExtractedData>> getAllExtractData();
}