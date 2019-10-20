package com.maliblo.fincam;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import static com.maliblo.fincam.Constants.TOTAL_AMOUNT;

public class AmaountImp {
    public static String setTotalValue(String value, Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TOTAL_AMOUNT, value);
        editor.commit();
        Log.i("AmountValue", "setTotalValue: "+value);
        return value;
    }

    public static String getTotalValue(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(TOTAL_AMOUNT, "-1");
    }
}