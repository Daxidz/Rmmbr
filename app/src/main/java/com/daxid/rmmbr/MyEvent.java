package com.daxid.rmmbr;

import java.util.GregorianCalendar;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;

/**
 * Created by David on 11.03.2017.
 */

public class MyEvent implements Serializable {
    private String subject;
    private String name;
    private String description;

    private GregorianCalendar deadline;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public MyEvent(String subject, String name, GregorianCalendar deadline) {
        this.subject = subject;
        this.name = name;
        this.deadline = deadline;
    }

    public String getSubject() {
        return subject;
    }

    public String getName() {
        return name;
    }

    public GregorianCalendar getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
