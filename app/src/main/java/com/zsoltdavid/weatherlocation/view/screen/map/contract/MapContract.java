package com.zsoltdavid.weatherlocation.view.screen.map.contract;

import com.google.android.gms.maps.model.LatLng;
import com.zsoltdavid.weatherlocation.data.model.current.Current;
import com.zsoltdavid.weatherlocation.view.common.contract.BasePresenter;
import com.zsoltdavid.weatherlocation.view.common.contract.BaseView;
import com.zsoltdavid.weatherlocation.view.screen.map.model.MapModel;

/**
 * Created by Admin on 2017.04.03..
 */

public final class MapContract {
    public MapContract() {

    }

    public interface View extends BaseView<MapModel> {
        void deleteSelectedMarker();
        void getLocation();

    }

    public interface Presenter extends BasePresenter {
        void createStoredMarker();

        void createSelectedMarker(LatLng position);

        void storeSelectedPosition(String latitude, String longitude);

        void createMarker(LatLng position);


        void deleteMarker();

        boolean hasStoredPosition();

        void getWeatherForLocation();
    }
}
