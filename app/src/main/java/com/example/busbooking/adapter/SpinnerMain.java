package com.example.busbooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class SpinnerMain extends ArrayAdapter<String> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<String> values;

    public SpinnerMain(Context context, int textViewResourceId,
                       List<String> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView) super.getView(position, convertView, parent);
        //label.setTextColor(Color.BLACK);
        label.setText(values.get(position));
        return label;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        //label.setTextColor(Color.BLACK);
        label.setText(values.get(position));
        return label;
    }
}
