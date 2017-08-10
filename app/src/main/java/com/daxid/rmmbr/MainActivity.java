package com.daxid.rmmbr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.daxid.rmmbr.utils.AdapterEvent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    // id for the add event
    private static final int NEW_EVENT = 1;

    public static final String EVENT_SELECTED = "event_selected";
    public static final int EVENT_SELECTED_CODE = 2;

    static int i = 0;
    private AdapterEvent ae;
    private List<MyEvent> eventList = new ArrayList<>();

    private static List<String> arraySubject = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        arraySubject.add("SER");
        arraySubject.add("GEN");
        arraySubject.add("PCO");

        final ListView lv = (ListView) findViewById(R.id.list_event);

        ae = new AdapterEvent(this, R.layout.event_layout, (ArrayList<MyEvent>) eventList);

        lv.setAdapter(ae);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                eventSelected(i);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save all data which you want to persist.
        StringBuilder savedList = new StringBuilder();
        for (String s : mList) {
            savedList.append(s);
            savedList.append(",");
        }
        getSharedPreferences(PREFS_TASKS, MODE_PRIVATE).edit()
                .putString(KEY_TASKS_LIST, savedList.toString()).commit();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_EVENT) {
            if (resultCode == RESULT_OK) {
                MyEvent event = (MyEvent)data.getSerializableExtra(CreateAnEvent.NEW_EVENT_CREATED);
                eventList.add(event);
                ae.notifyDataSetChanged();
            }
        } else if (requestCode == EVENT_SELECTED_CODE) {
            if (resultCode == ShowEventActivity.DELETE_EVENT) {
                eventList.remove(data.getIntExtra("delete_pos", 1));
                ae.notifyDataSetChanged();
            }
        }
    }

    private void eventSelected(final int position) {
        Intent intent = new Intent(this, ShowEventActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable(EVENT_SELECTED, eventList.get(position));

        intent.putExtras(bundle);
        intent.putExtra("position", position);

        startActivityForResult(intent, EVENT_SELECTED_CODE);
    }

    public void newEvent(View view) {

        Intent intent = new Intent(MainActivity.this, CreateAnEvent.class);
        startActivityForResult(intent, NEW_EVENT);
    }

    public static ArrayList<String> getArraySubjects() {
        return (ArrayList<String>) arraySubject;
    }
}
