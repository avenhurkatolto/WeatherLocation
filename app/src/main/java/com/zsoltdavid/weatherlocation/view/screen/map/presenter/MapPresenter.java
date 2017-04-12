package com.zsoltdavid.weatherlocation.view.screen.map.presenter;

import android.location.Location;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zsoltdavid.weatherlocation.data.local.PreferenceManager;
import com.zsoltdavid.weatherlocation.data.local.RxEventBus;
import com.zsoltdavid.weatherlocation.data.model.current.Current;
import com.zsoltdavid.weatherlocation.data.remote.LocationManager;
import com.zsoltdavid.weatherlocation.data.remote.WeatherApi;
import com.zsoltdavid.weatherlocation.view.common.custom.CustomSnackBar;
import com.zsoltdavid.weatherlocation.view.common.presenter.BasePresenter;
import com.zsoltdavid.weatherlocation.view.screen.map.contract.MapContract;
import com.zsoltdavid.weatherlocation.view.screen.map.model.MapModel;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 2017.04.03..
 */

public class MapPresenter extends BasePresenter<MapContract.View, MapModel>
        implements MapContract.Presenter, Consumer<RxEventBus.RxEvent>, LocationListener {

    private static final String TAG = "MapPresenter";

    @Override
    protected MapModel buildModel() {
        return new MapModel.Builder().createMapModel();
    }

    @Override
    public void attach(MapContract.View view) {
        super.attach(view);
        RxEventBus.getInstance().register(this);
    }

    @Override
    public void createStoredMarker() {
        if (hasStoredPosition())
            createMarker(new LatLng(Double.parseDouble(PreferenceManager.getInstance().getSelectedLatitude()),
                    Double.parseDouble(PreferenceManager.getInstance().getSelectedLongitude())));
        render();
    }

    @Override
    public void createSelectedMarker(LatLng position) {

        view.deleteSelectedMarker();
        LocationManager.getInstance().removeLocationUpdate(this);
        createMarker(position);
        render();
        storeSelectedPosition(Double.toString(position.latitude), Double.toString(position.longitude));
        Log.d(TAG, "createSelectedMarker: Latitude : " + position.latitude + ", Longitude: " + position.longitude);

    }

    @Override
    public void storeSelectedPosition(String latitude, String longitude) {
        PreferenceManager.getInstance().storeSelectedLatitude(latitude);
        PreferenceManager.getInstance().storeSelectedLongitude(longitude);
    }

    @Override
    public void createMarker(LatLng position) {
        addToModel(model.toBuilder()
                .setSelectedMarker(new MarkerOptions()
                        .position(position)
                        .title("Selected position")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))
                .createMapModel());

        LocationManager.getInstance().requestLocationUpdate(this);
    }

    @Override
    public void deleteMarker() {
        PreferenceManager.getInstance().storeSelectedLongitude("");
        PreferenceManager.getInstance().storeSelectedLatitude("");
    }

    @Override
    public boolean hasStoredPosition() {
        return !TextUtils.isEmpty(PreferenceManager.getInstance().getSelectedLatitude()) &&
                !TextUtils.isEmpty(PreferenceManager.getInstance().getSelectedLongitude());
    }

    @Override
    public void getWeatherForLocation() {
        WeatherApi.getInstance().getCurrentWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Current>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Current value) {
                        Log.d(TAG, "onSuccess: ");
                        addToModel(model.toBuilder()
                                .setCurrentWeather(value)
                                .createMapModel());
                     //   view.displayWeather(value);
                        render();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG, "onError: ");
                    }
                });

    }

    @Override
    public void accept(RxEventBus.RxEvent rxEvent) throws Exception {
        if (rxEvent.getEventId() == RxEventBus.RxEvent.RxEventId.GOOGLE_API_CONNECTED) {
            view.getLocation();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        addToModel(model.toBuilder()
                .setLastKnownLocation(new LatLng(location.getLongitude(), location.getLatitude()))
                .createMapModel());
        getWeatherForLocation();
        render();
    }
}
