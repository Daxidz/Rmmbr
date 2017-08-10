package com.daxid.rmmbr.utils;

import android.content.Context;
import java.util.GregorianCalendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daxid.rmmbr.MyEvent;
import com.daxid.rmmbr.R;
import com.daxid.rmmbr.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 11.03.2017.
 */

public class AdapterEvent extends ArrayAdapter<MyEvent> {
    private List<MyEvent> eventList = new ArrayList<>();

    public AdapterEvent(Context context, int textViewResourceId, ArrayList<MyEvent> objects) {
        super(context, textViewResourceId, objects);
        eventList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.event_layout, null);
        ((TextView) v.findViewById(R.id.subject)).setText(eventList.get(position).getSubject());
        ((TextView) v.findViewById(R.id.name)).setText(eventList.get(position).getName());

        GregorianCalendar gregorianCalendar = eventList.get(position).getDeadline();
        ((TextView) v.findViewById(R.id.date)).setText(DateUtils.format(eventList.get(position).getDeadline()));

        return v;
    }
}
