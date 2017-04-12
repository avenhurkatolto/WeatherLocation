package com.zsoltdavid.weatherlocation.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.zsoltdavid.weatherlocation.utils.TemperatureEnum;

/**
 * Created by Admin on 2017.04.05..
 */

public class PreferenceManager {
    public static final String OPEN_WEATHER_TEMPERATURE_KEY = "open_weather_temperature_key";

    private static final String preference_manager_selected_latitude_key = "preferenceManagerSelectedLatitudeKey";
    private static final String getPreference_manager_selected_longitude_key = "preferenceManagerSelectedLongitudeKey";

    private static PreferenceManager instance;
    private SharedPreferences sharedPreferences;

    private PreferenceManager(Context context) {
        this.sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public static void initialize(Context context) {
        if (instance == null)
            instance = new PreferenceManager(context);
    }

    public static PreferenceManager getInstance() {
        if (instance == null)
            throw new RuntimeException("You have to initialize preference manager first!");
        else
            return instance;
    }

    public String getSelectedLatitude() {
        return sharedPreferences.getString(preference_manager_selected_latitude_key, "");
    }

    public String getSelectedLongitude() {
        return sharedPreferences.getString(getPreference_manager_selected_longitude_key, "");
    }

    public void storeSelectedLatitude(String value) {
        sharedPreferences.edit().putString(preference_manager_selected_latitude_key, value).apply();
    }

    public void storeSelectedLongitude(String value) {
        sharedPreferences.edit().putString(getPreference_manager_selected_longitude_key, value).apply();
    }

    public void setTemperature(TemperatureEnum temperatureEnum) {
        sharedPreferences.edit().putInt(OPEN_WEATHER_TEMPERATURE_KEY, temperatureEnum.getValue()).apply();
    }

    public TemperatureEnum getTemperature() {
        return TemperatureEnum.forNumber(sharedPreferences.getInt(OPEN_WEATHER_TEMPERATURE_KEY, 0));
    }
}
