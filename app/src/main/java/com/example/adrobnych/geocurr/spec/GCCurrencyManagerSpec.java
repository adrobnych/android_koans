package com.example.adrobnych.geocurr.spec;

import com.example.adrobnych.geocurr.model.entities.GCCurrency;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyHTTPHelper;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyManager;
import com.example.adrobnych.geocurr.spec.db.TestDbHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by adrobnych on 3/25/15.
 */
public class GCCurrencyManagerSpec {

    static GCCurrencyManager cm = null;
    static ConnectionSource connectionSource = null;

    String xmlResponse = new StringBuilder()
            .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
            .append("   <ISO_4217 Pblshd=\"2015-01-01\">")
            .append("       <CcyTbl>")
            .append("           <CcyNtry>")
            .append("               <CtryNm>AFGHANISTAN</CtryNm>")
            .append("               <CcyNm>Afghani</CcyNm>")
            .append("               <Ccy>AFN</Ccy>")
            .append("               <CcyNbr>971</CcyNbr>")
            .append("               <CcyMnrUnts>2</CcyMnrUnts>")
            .append("           </CcyNtry>")
            .append("           <CcyNtry>")
            .append("               <CtryNm>ALBANIA</CtryNm>")
            .append("               <CcyNm>Lek</CcyNm>")
            .append("               <Ccy>ALL</Ccy>")
            .append("               <CcyNbr>008</CcyNbr>")
            .append("               <CcyMnrUnts>2</CcyMnrUnts>")
            .append("           </CcyNtry>")
            .append("       </CcyTbl>")
            .append("   </ISO_4217>").toString();

    @BeforeClass
    public static void setUpDatabaseLayer() throws SQLException {
        connectionSource = new TestDbHelper().getConnectionSource();
        TableUtils.createTableIfNotExists(connectionSource, GCCurrency.class);
        cm = new GCCurrencyManager();
        Dao<GCCurrency, String> currencyDao;
        try {
            currencyDao = DaoManager.createDao(connectionSource, GCCurrency.class);
            cm.setCurrencyDao(currencyDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    @BeforeClass
//    public static void setUpHTTPLayer() throws SQLException {
//        cm.setCurrencyHTTPHelper(new GCCurrencyHTTPHelper());
//    }


    @Before
    public void clearCurrencies() {
        try {
            TableUtils.clearTable(connectionSource, GCCurrency.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void itShouldLoadCurrenciesWithXMLString() throws SQLException {
        //empty table case

        cm.updateCurrenciesWithXMLString(xmlResponse);


        assertEquals("Afghani", cm.getCurrencyById("AFGHANISTAN").getName());
        assertEquals("Lek", cm.getCurrencyById("ALBANIA").getName());
    }


//    @Test
//    public void itShouldLoadXMLDataFromNetwork(){
//        String result = cm.getCurrencyHTTPHelper().getAllCurrencies();
//        assertTrue(result.contains("Euro"));
//    }
}