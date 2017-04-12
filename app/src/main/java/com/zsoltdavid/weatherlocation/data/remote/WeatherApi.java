package com.zsoltdavid.weatherlocation.data.remote;


import com.zsoltdavid.weatherlocation.data.model.current.Current;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Admin on 2016.09.23..
 */
public class WeatherApi implements WeatherApiInterface {

    private static WeatherApi instance;
    private WeatherRestInterface weatherApi;

    private WeatherApi() {
        weatherApi = RetrofitApi.getRetrofitClient().create(WeatherRestInterface.class);
    }

    public static synchronized WeatherApiInterface getInstance() {
        if (instance == null) {
            instance = new WeatherApi();
        }

        return instance;
    }

    public Single<Current> getCurrentWeather() {
        return Single.fromCallable(new Callable<Current>() {
            @Override
            public Current call() throws Exception {
                Response<Current> currentResponse = weatherApi.getCurrentWeather(RetrofitApi.API_VERSION_NUMBER).execute();
                if (!currentResponse.isSuccessful())
                    throw new Exception(currentResponse.raw().message());

                return currentResponse.body();
            }
        });
    }
}
