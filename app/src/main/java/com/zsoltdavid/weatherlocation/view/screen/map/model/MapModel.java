package com.zsoltdavid.weatherlocation.view.screen.map.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zsoltdavid.weatherlocation.data.model.current.Current;
import com.zsoltdavid.weatherlocation.view.common.model.BaseModel;

/**
 * Created by Admin on 2017.04.03..
 */

public class MapModel extends BaseModel {

    private MarkerOptions selectedMarker;
    private LatLng lastKnownLocation;
    private Current currentWeather;

    private MapModel(MarkerOptions selectedMarker, LatLng lastKnownLocation, Current currentWeather) {
        this.selectedMarker = selectedMarker;
        this.lastKnownLocation = lastKnownLocation;
        this.currentWeather = currentWeather;
    }

    public Current getCurrentWeather() {
        return currentWeather;
    }

    public MarkerOptions getSelectedMarker() {
        return selectedMarker;
    }

    public LatLng getLastKnownLocation() {
        return lastKnownLocation;
    }

    public Builder toBuilder() {
        return new Builder()
                .setLastKnownLocation(getLastKnownLocation())
                .setSelectedMarker(getSelectedMarker());
    }

    public static class Builder {
        private MarkerOptions selectedMarker;
        private LatLng lastKnownLocation;
        private Current currentWeather;

        public Builder setSelectedMarker(MarkerOptions selectedMarker) {
            this.selectedMarker = selectedMarker;
            return this;
        }

        public Builder setLastKnownLocation(LatLng lastKnownLocation) {
            this.lastKnownLocation = lastKnownLocation;
            return this;
        }

        public Builder setCurrentWeather(Current currentWeather) {
            this.currentWeather = currentWeather;
            return this;
        }

        public MapModel createMapModel() {
            return new MapModel(selectedMarker, lastKnownLocation, currentWeather);
        }
    }
}
