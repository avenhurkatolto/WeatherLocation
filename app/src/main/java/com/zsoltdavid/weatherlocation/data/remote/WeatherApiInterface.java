package com.zsoltdavid.weatherlocation.data.remote;

import com.zsoltdavid.weatherlocation.data.model.current.Current;

import io.reactivex.Single;

public interface WeatherApiInterface {
    Single<Current> getCurrentWeather();
}
