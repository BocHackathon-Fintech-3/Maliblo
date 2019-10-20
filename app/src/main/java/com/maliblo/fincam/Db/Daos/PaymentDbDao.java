package com.maliblo.fincam.Db.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.maliblo.fincam.Db.tables.PaymentsDB;


@Dao
public interface PaymentDbDao {
    @Insert
    void insert(PaymentsDB payments);

    @Update
    void update(PaymentsDB payments);

    @Delete
    void delete(PaymentsDB payments);


}
