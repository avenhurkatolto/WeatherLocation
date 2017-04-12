package com.zsoltdavid.weatherlocation.data.remote;

import com.zsoltdavid.weatherlocation.data.local.PreferenceManager;
import com.zsoltdavid.weatherlocation.utils.TemperatureEnum;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 2016.09.08..
 */
public class RetrofitApi {
    public static final String API_VERSION_NUMBER = "2.5";
    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static final String APP_ID = "appid";
    private static final String API_KEY = "c7d381c35b01024129be5146745786c5";
    private static final String UNITS_KEY = "units";
    private static final String LATITUDE_KEY = "lat";
    private static final String LONGITUDE_KEY = "lon";

    private static Retrofit retrofit;

    private RetrofitApi() {
    }

    public static synchronized Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(buildOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC).setLevel
                (HttpLoggingInterceptor.Level.BODY).setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request().newBuilder().url(chain.request().url().newBuilder()
                                .addQueryParameter(APP_ID, API_KEY)
                                .addQueryParameter(LATITUDE_KEY, String.valueOf(LocationManager.getInstance().getLastLocation().latitude))
                                .addQueryParameter(LONGITUDE_KEY,  String.valueOf(LocationManager.getInstance().getLastLocation().longitude))
                                .addQueryParameter(UNITS_KEY, unitsConverter(PreferenceManager.getInstance().getTemperature())).build())
                                .build());
                    }
                });
        return okBuilder.build();
    }

    private static String unitsConverter(TemperatureEnum temperatureEnum) {
        switch (temperatureEnum) {
            case Celsius:
                return "metric";
            case Fahrenheit:
                return "imperial";
            default:
                return "";
        }
    }
}
