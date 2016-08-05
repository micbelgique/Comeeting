package com.les4elefantastiq.les4elefantcowork.models;

import android.location.Location;

/**
 * Created by Math on 05/08/16.
 */
public class Coworkspace {
    String name;
    String pictureUrl;
    String description;
    Location geolocation;
    int geofancingRadius;
    Coworker[] coworkers;
}
