package com.les4elefantastiq.les4elefantcowork.models;

/**
 * Created by Math on 05/08/16.
 */
public class Coworkspace {
    public int id;
    public String name;
    public String pictureUrl;
    public String description;
    public Double geolocationLongitude; // TODO: Create a Location object ?
    public Double geolocationLatitude;
    public int geofancingRadius;
    public Coworker[] coworkers;

    public Coworkspace(int id, String name, String pictureUrl, String description, Double geolocationLatitude, Double geolocationLongitude, int geofancingRadius, Coworker[] coworkers) {
        this.id = id;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.geolocationLatitude = geolocationLatitude;
        this.geolocationLongitude = geolocationLongitude;
        this.geofancingRadius = geofancingRadius;
        this.coworkers = coworkers;
    }
}
