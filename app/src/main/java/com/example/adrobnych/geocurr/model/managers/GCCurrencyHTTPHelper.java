package com.example.adrobnych.geocurr.model.managers;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by adrobnych on 3/26/15.
 */
public class GCCurrencyHTTPHelper {
    private String url = "http://www.currency-iso.org/dam/downloads/table_a1.xml";
    private static HttpClient httpClient = new DefaultHttpClient();
    public String getAllCurrencies() {

        HttpGet request = new HttpGet(url);
        HttpResponse get_response = null;
        try {
            get_response = httpClient.execute(request);
        }   catch (IOException e) {
            e.printStackTrace();
        }

        InputStream iStream = null;
        String result = null;

        try {
            iStream = get_response.getEntity().getContent();
            result = IOUtils.toString(iStream, "UTF-8");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }
}
