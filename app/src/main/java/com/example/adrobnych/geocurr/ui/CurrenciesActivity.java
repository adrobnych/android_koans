package com.example.adrobnych.geocurr.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.adrobnych.geocurr.GCApp;
import com.example.adrobnych.geocurr.R;
import com.example.adrobnych.geocurr.adapters.CurrencyAdapter;
import com.example.adrobnych.geocurr.adapters.CurrencyPagerAdapter;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyHTTPHelper;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyManager;
import com.example.adrobnych.geocurr.services.LoadCurrenciesService;

import java.util.HashMap;
import java.util.Map;


public class CurrenciesActivity extends ActionBarActivity {

    private GCCurrencyManager cm;
    private String[] stateArr = {"list", "note"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);
        cm = ((GCApp) getApplication()).getCurrencyManager();



        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new CurrencyPagerAdapter(getSupportFragmentManager(), stateArr, getAllFragments()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_currencies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            Intent intent = new Intent(this, LoadCurrenciesService.class);
            startService(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Map<String, Fragment> getAllFragments() {
        Map<String, Fragment> fragmentMap = new HashMap<String, Fragment>();
        fragmentMap.put(stateArr[0], new FragmentCurremciesList());
        fragmentMap.put(stateArr[1], new FragmentCurrencyNote());

        return fragmentMap;

    }
}