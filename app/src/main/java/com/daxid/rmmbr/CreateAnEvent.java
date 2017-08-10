package com.daxid.rmmbr;

import android.content.Intent;
import java.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.daxid.rmmbr.utils.MyEditTextDate;
import com.daxid.rmmbr.utils.MyEditTextHour;


public class CreateAnEvent extends AppCompatActivity {

    public static final String NEW_EVENT_CREATED = "NEW_EVENT";
    MyEditTextDate myEditTextDate;
    MyEditTextHour myEditTextHour;

    private int subject_selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_an_event);

        myEditTextDate = new MyEditTextDate(this, R.id.date_edit);

        myEditTextHour = new MyEditTextHour(this, R.id.hour_edit);

        Spinner spinnerSubjects = (Spinner) findViewById(R.id.spinner_subjects);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MainActivity.getArraySubjects());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubjects.setAdapter(adapter);


        spinnerSubjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                subject_selected = pos;
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onOkClick(View view) {
        // create the intent
        Intent intent = new Intent();
       // EditText subject = (EditText) findViewById(R.id.subject_edit);
        String subject = MainActivity.getArraySubjects().get(subject_selected);
        EditText title = (EditText) findViewById(R.id.title_edit);
        if (subject.isEmpty() || title.getText().toString().isEmpty()) {
            Toast.makeText(this, "Champ(s) manquant!", Toast.LENGTH_SHORT).show();
            return;
        }
        GregorianCalendar calendar = new GregorianCalendar(myEditTextDate.getYear(),
                myEditTextDate.getMonth(), myEditTextDate.getDay(),
                myEditTextHour.getHour(), myEditTextHour.getMinutes());

        MyEvent event = new MyEvent(subject, title.getText().toString(), calendar);

        Bundle bundle = new Bundle();
        bundle.putSerializable(NEW_EVENT_CREATED, event);

        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);

        finish();
    }

}
