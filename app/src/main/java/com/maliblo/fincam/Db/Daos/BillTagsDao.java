package com.maliblo.fincam.Db.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.maliblo.fincam.Db.tables.BillTags;


@Dao
public interface BillTagsDao {
    @Insert
    void insert(BillTags tags);

    @Update
    void update(BillTags tags);

    @Delete
    void delete(BillTags tags);
}
