package com.example.adrobnych.geocurr.services;

import android.app.IntentService;
import android.content.Intent;

import com.example.adrobnych.geocurr.model.managers.GCCurrencyHTTPHelper;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyManager;


public class LoadCurrenciesService extends IntentService {
    private String result = null;
    public final static String RESULT = "result";
    public static final String NOTIFICATION = "com.example.adrobnych.geocurr.services.loadcurrencies.receiver";

    public LoadCurrenciesService() {
        super("LoadCurrenciesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        GCCurrencyHTTPHelper ch = new GCCurrencyHTTPHelper();
        result = ch.getAllCurrencies();
        publishResults(result);
    }

    private void publishResults(String result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
