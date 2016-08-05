package com.les4elefantastiq.les4elefantcowork.models;

/**
 * Created by Math on 05/08/16.
 */
public class Coworker {
    public String firstName;
    public String lastName;
    public String pictureUrl;
    public String summary;

    public Coworker(String firstName, String lastName, String pictureUrl, String summary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.summary = summary;
    }
}
