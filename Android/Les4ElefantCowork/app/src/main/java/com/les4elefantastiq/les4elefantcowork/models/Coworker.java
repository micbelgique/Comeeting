package com.les4elefantastiq.les4elefantcowork.models;

import com.google.gson.annotations.SerializedName;
import com.les4elefantastiq.les4elefantcowork.models.linkedinmodels.PictureUrls;
import com.les4elefantastiq.les4elefantcowork.models.linkedinmodels.Positions;

import java.util.List;

/**
 * Created by Math on 05/08/16.
 */
public class Coworker {

    // -------------- Objects, Variables -------------- //

    @SerializedName(value = "id")
    public String linkedInId;
    public String firstName;
    public String lastName;
    public String pictureUrl;
    public String summary;
    public String headline;
    public List<Position> positions;

    @SerializedName(value = "favoriteCoworkspaces")
    List<String> favoriteCoworkspacesId;

    @SerializedName(value = "currentCoworkspace")
    public String currentCoworkspaceId;


    // ----------------- Constructor ------------------ //

    public Coworker(String linkedInId, String firstName, String lastName, String pictureUrl, String summary, String headline, List<Position> positions, List<String> favoriteCoworkspacesId, String currentCoworkspaceId) {
        this.linkedInId = linkedInId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.summary = summary;
        this.headline = headline;
        this.positions = positions;
        this.favoriteCoworkspacesId = favoriteCoworkspacesId;
        this.currentCoworkspaceId = currentCoworkspaceId;
    }

    // ---------------- Public Methods ---------------- //

    // ---------------- Private Methods --------------- //

    // ----------------- Miscellaneous ---------------- //

}
