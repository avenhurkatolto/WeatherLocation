package com.zsoltdavid.weatherlocation.utils;

/**
 * Created by Admin on 2017.03.27..
 */

public enum TemperatureEnum {
    Celsius(0),
    Kelvin(1),
    Fahrenheit(2);

    private int value;

    TemperatureEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TemperatureEnum forNumber(int number) {
        switch (number) {
            case 0:
                return Celsius;
            case 1:
                return Kelvin;
            case 2:
                return Fahrenheit;
            default:
                return Celsius;
        }
    }
}
