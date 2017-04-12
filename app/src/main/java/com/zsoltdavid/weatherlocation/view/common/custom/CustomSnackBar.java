package com.zsoltdavid.weatherlocation.view.common.custom;

import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zsoltdavid.weatherlocation.R;
import com.zsoltdavid.weatherlocation.data.model.current.Current;

/**
 * Created by risko on 2017.04.10..
 */

public final class CustomSnackBar
        extends BaseTransientBottomBar<CustomSnackBar> {


    protected CustomSnackBar(@NonNull ViewGroup parent, @NonNull View content, @NonNull ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    public static CustomSnackBar make(ViewGroup parent, int duration, Current current) {
        // inflate custom layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_snackbar, parent, false);


        // create with custom view
        ContentViewCallback callback = new ContentViewCallback(view);
        CustomSnackBar CustomSnackBar = new CustomSnackBar(parent, view, callback);

        CustomSnackBar.setDuration(duration);

        setLabelsByCurrent(current, view);

        return CustomSnackBar;
    }

    private static void setLabelsByCurrent(Current current, View view) {
        ((TextView) view.findViewById(R.id.snackbar_clouds)).setText("Clouds: " + current.getClouds().getAll());
        ((TextView) view.findViewById(R.id.snackbar_location)).setText("Location: " + current.getName());
        ((TextView) view.findViewById(R.id.snackbar_humidity)).setText("Humidity: " + current.getMain().getHumidity());
        ((TextView) view.findViewById(R.id.snackbar_temperature)).setText("Temperature: " + current.getMain().getTemp());


    }


    private static class ContentViewCallback
            implements BaseTransientBottomBar.ContentViewCallback {

        // view inflated from custom layout
        private View view;

        public ContentViewCallback(View view) {
            this.view = view;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            // TODO: handle enter animation
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            // TODO: handle exit animation
        }
    }


}