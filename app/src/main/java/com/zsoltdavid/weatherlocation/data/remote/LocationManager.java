package com.zsoltdavid.weatherlocation.data.remote;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.zsoltdavid.weatherlocation.data.local.RxEventBus;

import timber.log.Timber;

/**
 * Created by Admin on 2017.04.06..
 */

public class LocationManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static LocationManager instance;
    private GoogleApiClient googleApiClient;

    private LocationManager(Context context) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();
    }

    public static void initialize(Context context) {
        if (instance == null)
            instance = new LocationManager(context.getApplicationContext());
    }

    public static LocationManager getInstance() {
        if (instance == null)
            throw new RuntimeException("You have to initialize location manager first!");
        else
            return instance;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Timber.d("connected");
        RxEventBus.getInstance().send(RxEventBus.RxEvent.create(RxEventBus.RxEvent.RxEventId.GOOGLE_API_CONNECTED));
    }

    @Override
    public void onConnectionSuspended(int i) {
        Timber.d("connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.d("connection failed " + connectionResult.getErrorMessage());
        RxEventBus.getInstance().send(RxEventBus.RxEvent.create(RxEventBus.RxEvent.RxEventId.GOOGLE_API_CONNECTION_ERROR, connectionResult.getErrorMessage()));
    }

    public LatLng getLastLocation() throws SecurityException{
        return new LatLng(LocationServices.FusedLocationApi.getLastLocation(googleApiClient).getLatitude(),
                LocationServices.FusedLocationApi.getLastLocation(googleApiClient).getLongitude());
    }

    public void requestLocationUpdate(LocationListener locationListener) throws SecurityException{
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, new LocationRequest().setInterval(10000).setFastestInterval(10000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY), locationListener);
    }

    public void removeLocationUpdate(LocationListener locationListener) {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, locationListener);
    }
}
