package com.les4elefantastiq.les4elefantcowork.models;

/**
 * Created by Math on 05/08/16.
 */
public class Coworkspace {
    public String name;
    public String pictureUrl;
    public String description;
    public Double geolocationLongitude; // TODO: Create a Location object ?
    public Double geolocationLatitude;
    public int geofancingRadius;
    public Coworker[] coworkers;

    public Coworkspace(String name, String pictureUrl, String description, Double geolocationLatitude, Double geolocationLongitude, int geofancingRadius, Coworker[] coworkers) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.geolocationLatitude = geolocationLatitude;
        this.geolocationLongitude = geolocationLongitude;
        this.geofancingRadius = geofancingRadius;
        this.coworkers = coworkers;
    }
}
