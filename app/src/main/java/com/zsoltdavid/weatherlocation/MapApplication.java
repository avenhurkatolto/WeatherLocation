package com.zsoltdavid.weatherlocation;

import android.app.Application;

import com.google.android.gms.maps.MapsInitializer;
import com.zsoltdavid.weatherlocation.data.local.PreferenceManager;
import com.zsoltdavid.weatherlocation.data.remote.LocationManager;
import com.zsoltdavid.weatherlocation.utils.SystemUtil;

import timber.log.Timber;

/**
 * Created by Admin on 2017.04.03..
 */

public class MapApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MapsInitializer.initialize(getApplicationContext());
        PreferenceManager.initialize(getApplicationContext());
        if (SystemUtil.checkPlayServices(getApplicationContext()))
            LocationManager.initialize(getApplicationContext());

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
    }
}
