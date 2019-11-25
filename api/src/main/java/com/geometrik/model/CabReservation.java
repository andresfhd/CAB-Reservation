/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geometrik.model;

/**
 *
 * @author andreshurtadodiez
 */
public class CabReservation {
    private double latitude;
    private double longitude;
    private String locationName;
    private boolean holdBooking;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean isHoldBooking() {
        return holdBooking;
    }

    public void setHoldBooking(boolean holdBooking) {
        this.holdBooking = holdBooking;
    }
}
