package com.example.adrobnych.geocurr;

import android.app.Application;
import android.util.Log;

import com.example.adrobnych.geocurr.db.GCDBHelper;
import com.example.adrobnych.geocurr.model.entities.GCCurrency;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyHTTPHelper;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

/**
 * Created by adrobnych on 3/26/15.
 */
public class GCApp extends Application {
    private static final String TAG = "com.droidbrew.travelcheap.TravelApp";
    private volatile GCCurrencyManager currencyManager = null;
    private GCDBHelper dbHelper = null;


    public GCDBHelper getDbHelper() {
        return dbHelper;
    }

    public GCApp() {
        super();
        try {
            dbHelper = new GCDBHelper(this);
        } catch (SQLException e) {
            Log.e(TAG, "getDBHelper", e);
        }
    }

    public GCCurrencyManager getCurrencyManager(){
        if (null == currencyManager)
            synchronized(this){
                if (null == currencyManager) {

                    currencyManager = new GCCurrencyManager();
                    Dao<GCCurrency, String> currencyDao;
                    try {
                        currencyDao = DaoManager.createDao(dbHelper.getConnectionSource(), GCCurrency.class);
                        currencyManager.setCurrencyDao(currencyDao);
                        currencyManager.setCurrencyHTTPHelper(new GCCurrencyHTTPHelper());
                    } catch (SQLException e) {
                        Log.e(TAG, "getExpenseManager", e);
                    }
                }

        }
        return currencyManager;
    }

}