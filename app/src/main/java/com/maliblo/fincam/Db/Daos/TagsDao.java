package com.maliblo.fincam.Db.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.maliblo.fincam.Db.tables.Tags;


@Dao
public interface TagsDao {
    @Insert
    void insert(Tags tags);

    @Update
    void update(Tags tags);

    @Delete
    void delete(Tags tags);


}
