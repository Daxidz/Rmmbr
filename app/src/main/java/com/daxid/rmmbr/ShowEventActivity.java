package com.daxid.rmmbr;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class ShowEventActivity extends AppCompatActivity {

    static final int DELETE_EVENT = 2;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_event);

        MyEvent event = (MyEvent)getIntent().getSerializableExtra(MainActivity.EVENT_SELECTED);

        GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();

        GregorianCalendar deadline = event.getDeadline();

        long timeLeft = deadline.getTimeInMillis() - now.getTimeInMillis();
        long hours = timeLeft / (1000 * 60 *60) % 24;
        long days = timeLeft / (1000 * 60 *60 * 24);


        String timeLeftStr = days + " jours" + " " + hours + " heures";

        TextView textView = (TextView) findViewById(R.id.nbr_days);
        TextView title = (TextView) findViewById(R.id.title_show);

        title.setText(event.getSubject() + " " + event.getName());
        textView.setText(timeLeftStr);
    }

    @Override protected void onStop() {
        super.onStop();
        setResult(RESULT_OK);

        finish();
    }

    public void deleteEvent(View view) {
        Intent intent = new Intent();

        intent.putExtra("delete_pos", getIntent().getIntExtra("position", 1));

        setResult(DELETE_EVENT, intent);

        finish();
    }


}
