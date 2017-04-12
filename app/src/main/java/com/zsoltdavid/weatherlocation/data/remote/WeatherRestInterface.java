package com.zsoltdavid.weatherlocation.data.remote;

import com.zsoltdavid.weatherlocation.data.model.current.Current;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Admin on 2016.09.23..
 */
interface WeatherRestInterface {
    @GET("data/{version}/weather")
    Call<Current> getCurrentWeather(@Path("version") String version);
}
