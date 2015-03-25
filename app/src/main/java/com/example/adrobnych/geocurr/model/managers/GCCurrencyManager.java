package com.example.adrobnych.geocurr.model.managers;

import com.example.adrobnych.geocurr.model.entities.GCCurrency;
import com.j256.ormlite.dao.Dao;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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

    public void updateCurrenciesWithXMLString(String xmlResponse) throws SQLException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputSource is;
        try {
            builder = factory.newDocumentBuilder();

            is = new InputSource(new StringReader(xmlResponse));
            Document doc = null;


            doc = builder.parse(is);


            final NodeList list = doc.getElementsByTagName("CcyNtry");

            currencyDao.callBatchTasks(new Callable<Void>() {
                public Void call() throws Exception {
                    for (int i = 0; i < list.getLength(); i++) {
                        String country = list.item(i).getChildNodes().item(1).getTextContent();
                        String name = list.item(i).getChildNodes().item(3).getTextContent();
                        String code = list.item(i).getChildNodes().item(5).getTextContent();
                        GCCurrency c = new GCCurrency(code, name, country, false);
                        currencyDao.create(c);
                    }
                    return null;
                }
            });

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public GCCurrency getCurrencyById(String code) throws SQLException {
        return currencyDao.queryForId(code);
    }
}
