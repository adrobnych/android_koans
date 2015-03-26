package com.example.adrobnych.geocurr.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adrobnych.geocurr.GCApp;
import com.example.adrobnych.geocurr.R;
import com.example.adrobnych.geocurr.adapters.CurrencyAdapter;
import com.example.adrobnych.geocurr.model.managers.GCCurrencyManager;

public class FragmentCurremciesList extends Fragment {
    public ListView getListView() {
        return listView;
    }

    private ListView listView;
    private  GCCurrencyManager cm;
    private Context context;

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
        listView.deferNotifyDataSetChanged();
        CurrencyAdapter ad = new CurrencyAdapter(context,
                cm.getCache());
       listView.setAdapter(ad);
       listView.deferNotifyDataSetChanged();
    }
}
