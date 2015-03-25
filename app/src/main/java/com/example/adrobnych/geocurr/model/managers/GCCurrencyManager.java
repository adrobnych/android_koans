package com.example.adrobnych.geocurr.model.managers;

import com.example.adrobnych.geocurr.model.entities.GCCurrency;
import com.j256.ormlite.dao.Dao;

/**
 * Created by adrobnych on 3/25/15.
 */
public class GCCurrencyManager {
    private Dao<GCCurrency, String> currencyDao = null;

    public Dao<GCCurrency, String> getCurrencyDao() {
        return currencyDao;
    }

    public void setCurrencyDao(Dao<GCCurrency, String> currencyDao) {
        this.currencyDao = currencyDao;
    }

    public void updateCurrenciesWithXMLString(String xmlResponse) {
    }

    public GCCurrency getCurrencyById(String all) {
        return null;
    }
}
