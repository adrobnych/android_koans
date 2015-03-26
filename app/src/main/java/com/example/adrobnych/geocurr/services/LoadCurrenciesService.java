package com.example.adrobnych.geocurr.services;

import android.app.IntentService;
import android.content.Intent;

import com.example.adrobnych.geocurr.GCApp;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyHTTPHelper;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyManager;

import java.sql.SQLException;


public class LoadCurrenciesService extends IntentService {
    private String result = null;
    public final static String RESULT = "result";
    public static final String NOTIFICATION = "com.example.adrobnych.geocurr.services.loadcurrencies.receiver";

    public LoadCurrenciesService() {
        super("LoadCurrenciesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GCCurrencyManager cm = ((GCApp)getApplication()).getCurrencyManager();
        GCCurrencyHTTPHelper ch = cm.getCurrencyHTTPHelper();
        result = ch.getAllCurrencies();
        try {
            cm.updateCurrenciesWithXMLString(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        publishResults(result);
    }

    private void publishResults(String result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
