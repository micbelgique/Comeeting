package com.les4elefantastiq.les4elefantcowork.models.linkedinmodels;

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
        return month != 0 && year != 0 ? "Date: " + month + "/" + year : null;
    }
}
