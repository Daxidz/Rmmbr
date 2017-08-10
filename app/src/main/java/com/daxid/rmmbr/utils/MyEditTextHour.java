package com.daxid.rmmbr.utils;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.icu.util.TimeZone.getDefault;

/**
 * Created by David on 12.03.2017.
 */

public class MyEditTextHour  implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    EditText _editText;
    private int hour;
    private int minutes;
    private Context _context;

    public MyEditTextHour(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this._editText = (EditText)act.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = context;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour = hourOfDay;
        minutes = minute;
        updateDisplay();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog dialog = new TimePickerDialog(_context, this,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        dialog.show();

    }

    // update the date
    private void updateDisplay() {
        _editText.setText(new StringBuilder()
                .append(String.format("%02d", hour)).append(":").append(String.format("%02d", minutes)));
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }
}