package com.les4elefantastiq.les4elefantcowork.models;

/**
 * Created by Math on 05/08/16.
 */
public class Coworker {
    public String firstname;
    public String lastname;
    public String pictureUrl;
    public String summary;

    public Coworker(String firstname, String lastname, String pictureUrl, String summary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pictureUrl = pictureUrl;
        this.summary = summary;
    }
}
