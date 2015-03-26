package com.example.adrobnych.geocurr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrobnych.geocurr.R;
import com.example.adrobnych.geocurr.model.entities.GCCurrency;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by adrobnych on 3/26/15.
 */
public class CurrencyAdapter extends BaseAdapter {

    private class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

    private LayoutInflater inflater;

    private  List<GCCurrency> currencies;

    public CurrencyAdapter(Context context, List<GCCurrency> currencies) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.currencies = currencies;
    }

    @Override
    public int getCount() {
        if (currencies != null) {
            return currencies.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (currencies != null && position >= 0 && position < getCount()) {
            return currencies.get(position);
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        if (currencies != null && position >= 0 && position < getCount()) {
            return position;
        }

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View       view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.item_currency_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.list_image);
            viewHolder.textView = (TextView) view.findViewById(R.id.list_label);

            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        GCCurrency record = currencies.get(position);

        viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
        viewHolder.textView.setText(record.getName());

        return view;
    }

}