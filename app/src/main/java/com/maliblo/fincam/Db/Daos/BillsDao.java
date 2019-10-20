package com.maliblo.fincam.Db.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.maliblo.fincam.Db.tables.Bills;


@Dao
public interface BillsDao{
    @Insert
    void insert(Bills bills);

    @Update
    void update(Bills bills);

    @Delete
    void delete(Bills bills);
}
