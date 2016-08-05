package com.les4elefantastiq.les4elefantcowork.models;

import java.util.List;

/**
 * Created by Math on 05/08/16.
 */
public class Coworker {

    // -------------- Objects, Variables -------------- //

    public String linkedInId;
    public String firstName;
    public String lastName;
    public String pictureUrl;
    public String summary;
    public List<String> favoriteCoworkspacesId;

    // ----------------- Constructor ------------------ //

    public Coworker(String linkedInId, String firstName, String lastName, String pictureUrl, String summary, List<String> favoriteCoworkspacesId) {
        this.linkedInId = linkedInId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.summary = summary;
        this.favoriteCoworkspacesId = favoriteCoworkspacesId;
    }

    // ---------------- Public Methods ---------------- //

    // ---------------- Private Methods --------------- //

    // ----------------- Miscellaneous ---------------- //
    
}
