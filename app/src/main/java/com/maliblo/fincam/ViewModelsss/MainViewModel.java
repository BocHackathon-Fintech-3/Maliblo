package com.maliblo.fincam.ViewModelsss;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.maliblo.fincam.Db.Daos.ExtractedDataDao;
import com.maliblo.fincam.Db.DataBaseAPP;
import com.maliblo.fincam.Db.tables.ExtractedData;

import java.util.List;


public class MainViewModel extends AndroidViewModel {
    LiveData<List<ExtractedData>> data;
    ExtractedDataDao extractedDataDao;


    public MainViewModel(@NonNull Application application) {
        super(application);
        DataBaseAPP dataBaseAPP = DataBaseAPP.getInstance(application);
        extractedDataDao = dataBaseAPP.extractDataDao();
        data = extractedDataDao.getAllExtractData();
    }

    public LiveData<List<ExtractedData>> getExtractData() {
        return data;
    }
}
