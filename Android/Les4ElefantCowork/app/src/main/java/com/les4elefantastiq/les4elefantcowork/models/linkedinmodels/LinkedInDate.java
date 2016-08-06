package com.les4elefantastiq.les4elefantcowork.models.linkedinmodels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Math on 06/08/16.
 */
public class LinkedInDate {

    int month;
    int year;

    public LinkedInDate(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.getDefault()).format(new Date());
        //month != 0 && year != 0 ? "Date: " + month + "/" + year : null;
    }
}
