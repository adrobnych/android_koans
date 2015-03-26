package com.example.adrobnych.geocurr.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adrobnych.geocurr.GCApp;
import com.example.adrobnych.geocurr.R;
import com.example.adrobnych.geocurr.adapters.CurrencyAdapter;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyManager;
import com.example.adrobnych.geocurr.services.LoadCurrenciesService;

public class FragmentCurremciesList extends Fragment {
    public ListView getListView() {
        return listView;
    }

    private ListView listView;
    private  GCCurrencyManager cm;
    private Context context;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String result = bundle.getString(LoadCurrenciesService.RESULT);
                if (result != null) {
                    Toast.makeText(getActivity(),
                            "Download complete",
                            Toast.LENGTH_LONG).show();
                    //((FragmentCurremciesList)getAllFragments().get("list")).getListView().setAdapter(new CurrencyAdapter(getApplicationContext(), cm.getCache()));
                    //((FragmentCurremciesList)getAllFragments().get("list")).getListView().deferNotifyDataSetChanged();
                    listView.setAdapter(
                            new CurrencyAdapter(
                                    context,
                                    cm.getCache()));
                    listView.deferNotifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "Download failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cm = ((GCApp) getActivity().getApplication()).getCurrencyManager();

        View view =  inflater.inflate(R.layout.fragment_curremcies_list, container, false);

        context = view.getContext();
        listView = (ListView) view.findViewById(R.id.list_of_currencies);
        listView.setAdapter(
                new CurrencyAdapter(
                        context,
                cm.getCache()));
        listView.deferNotifyDataSetChanged();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, new IntentFilter(LoadCurrenciesService.NOTIFICATION));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }
}
