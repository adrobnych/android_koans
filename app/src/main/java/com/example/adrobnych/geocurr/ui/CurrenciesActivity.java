package com.example.adrobnych.geocurr.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrobnych.geocurr.GCApp;
import com.example.adrobnych.geocurr.R;
import com.example.adrobnych.geocurr.adapters.CurrencyAdapter;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyHTTPHelper;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyManager;
import com.example.adrobnych.geocurr.services.LoadCurrenciesService;


public class CurrenciesActivity extends ActionBarActivity {
    private ListView listView;
    private GCCurrencyManager cm;
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String result = bundle.getString(LoadCurrenciesService.RESULT);
                if (result != null) {
                    Toast.makeText(CurrenciesActivity.this,
                            "Download complete",
                            Toast.LENGTH_LONG).show();
                    listView.setAdapter(new CurrencyAdapter(getApplicationContext(), cm.getCache()));
                    listView.deferNotifyDataSetChanged();
                } else {
                    Toast.makeText(CurrenciesActivity.this, "Download failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);
        cm = ((GCApp)getApplication()).getCurrencyManager();
        listView = (ListView) findViewById(R.id.list_of_currencies);
        listView.setAdapter(new CurrencyAdapter(this, cm.getCache()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(LoadCurrenciesService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
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
}
