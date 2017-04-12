package com.zsoltdavid.weatherlocation.view.screen.map.gui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.zsoltdavid.weatherlocation.R;
import com.zsoltdavid.weatherlocation.data.remote.LocationManager;
import com.zsoltdavid.weatherlocation.utils.SystemUtil;
import com.zsoltdavid.weatherlocation.view.common.custom.CustomSnackBar;
import com.zsoltdavid.weatherlocation.view.common.gui.BaseFragment;
import com.zsoltdavid.weatherlocation.view.screen.map.contract.MapContract;
import com.zsoltdavid.weatherlocation.view.screen.map.model.MapModel;
import com.zsoltdavid.weatherlocation.view.screen.map.presenter.MapPresenter;

/**
 * Created by Admin on 2017.04.03..
 */

public class MapFragment extends BaseFragment<MapModel, MapPresenter>
        implements MapContract.View, OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final String TAG = "MapFragment";
    private static final int REQUEST_LOCATION_PERMISSION_CODE = 110;

    private GoogleMap map;
    private Marker selectedMarker;


    @Override
    public MapPresenter presenter() {
        return new MapPresenter();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_map;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void presentView(MapModel model) {
        if (model.getSelectedMarker() != null) {
            if (model.getCurrentWeather() != null) {
                CustomSnackBar customSnackbar = CustomSnackBar.make((ViewGroup) this.getView(), CustomSnackBar.LENGTH_INDEFINITE, model.getCurrentWeather());
                customSnackbar.show();
            }
            if (selectedMarker == null)
                selectedMarker = map.addMarker(model.getSelectedMarker());

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    if (SystemUtil.isGpsEnabled(getActivity())) {
                        getLocation();
                    } else {
                        showError("Location permission was denied.");
                    }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        presenter.createSelectedMarker(latLng);
    }

    @Override
    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission
                    .ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION_CODE);
            return;
        }
        Log.d(TAG, "getLocation: ");
        presenter.createStoredMarker();
        map.setOnMapClickListener(this);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LocationManager.getInstance().getLastLocation(), 16f));
        map.setMyLocationEnabled(false);
    }


    @Override
    public void deleteSelectedMarker() {
        if (selectedMarker != null) {
            presenter.deleteMarker();
            selectedMarker.remove();
            selectedMarker = null;
        }
    }
}
