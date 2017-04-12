package com.zsoltdavid.weatherlocation.utils;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by Admin on 2017.03.28..
 */

public final class ResourceManager {

    private static ResourceManager instance;
    private Context context;

    private ResourceManager() {
    }

    public static synchronized void initialize(Context context) {
        if (instance == null)
            instance = new ResourceManager();

        instance.context = context.getApplicationContext();
    }

    public static ResourceManager getInstance() {
        if (instance == null)
            throw new NullPointerException("You forgot to initialize resource manager.");

        return instance;
    }

    public String getString(@StringRes int id) {
        return context.getString(id);
    }
}
