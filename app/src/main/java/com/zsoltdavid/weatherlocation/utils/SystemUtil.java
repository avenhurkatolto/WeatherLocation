package com.zsoltdavid.weatherlocation.utils;

import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by Admin on 2016.10.06..
 */
public class SystemUtil {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    public static boolean isGpsEnabled(Context context) {
        return ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE)).isProviderEnabled
                (LocationManager.GPS_PROVIDER);
    }

    public static boolean checkPlayServices(Context context) {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(context);
        return result == ConnectionResult.SUCCESS;
    }
}
