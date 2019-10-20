package com.maliblo.fincam.Db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.maliblo.fincam.Db.Daos.BillTagsDao;
import com.maliblo.fincam.Db.Daos.BillsDao;
import com.maliblo.fincam.Db.Daos.ExtractedDataDao;
import com.maliblo.fincam.Db.Daos.PaymentDbDao;
import com.maliblo.fincam.Db.Daos.TagsDao;
import com.maliblo.fincam.Db.tables.BillTags;
import com.maliblo.fincam.Db.tables.Bills;
import com.maliblo.fincam.Db.tables.ExtractedData;
import com.maliblo.fincam.Db.tables.PaymentsDB;
import com.maliblo.fincam.Db.tables.Tags;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.maliblo.fincam.Constants.DB_NAME;


@Database(version = 1, entities = {Bills.class, BillTags.class, ExtractedData.class, PaymentsDB.class, Tags.class},exportSchema = false)

public abstract class DataBaseAPP extends RoomDatabase {

    public static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static DataBaseAPP instance;
    private static String TAG = DataBaseAPP.class.getSimpleName();

    public abstract BillsDao billsDao();

    public abstract BillTagsDao billTagsDao();

    public abstract ExtractedDataDao extractDataDao();

    public abstract PaymentDbDao paymentDbDao();

    public abstract TagsDao tagsDao();

    public static synchronized DataBaseAPP getInstance(Context context) {
        if (instance == null) {
            Log.i(TAG, "getInstance: Synchronized called");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DataBaseAPP.class, DB_NAME)
                    //.addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5,MIGRATION_5_6, MIGRATION_6_7)
                    //.fallbackToDestructiveMigration()
                    //.addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public static void insertBills(final Bills bills, final Context context) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                BillsDao billsDao;
                billsDao = getInstance(context).billsDao();
                billsDao.insert(bills);
            }
        });
    }

    public static void insertBillTags(final BillTags billTags, final Context context) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                BillTagsDao billsDao;
                billsDao = getInstance(context).billTagsDao();
                billsDao.insert(billTags);
            }
        });
    }

    public static void insertExtractedData(final ExtractedData extractedData, final Context context) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                ExtractedDataDao billsDao;
                billsDao = getInstance(context).extractDataDao();
                billsDao.insert(extractedData);
            }
        });
    }

    public static void extractedData(final ExtractedData extractedData, final Context context) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                ExtractedDataDao billsDao;
                billsDao = getInstance(context).extractDataDao();
                billsDao.insert(extractedData);
            }
        });
    }

    public static void insertPayment(final PaymentsDB payments, final Context context) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                PaymentDbDao paymentDbDao;
                paymentDbDao = getInstance(context).paymentDbDao();
                paymentDbDao.insert(payments);
            }
        });
    }

    public static void insertTags(final Tags tags, final Context context) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                TagsDao tagsDao;
                tagsDao = getInstance(context).tagsDao();
                tagsDao.insert(tags);
            }
        });
    }
}
