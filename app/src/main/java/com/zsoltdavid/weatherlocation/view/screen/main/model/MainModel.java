package com.zsoltdavid.weatherlocation.view.screen.main.model;

import com.zsoltdavid.weatherlocation.view.common.model.BaseModel;

/**
 * Created by Admin on 2017.04.03..
 */

public class MainModel extends BaseModel {

    private MainModel() {}

    public Builder toBuilder(){
        return new Builder();
    }

    public static class Builder {

        public MainModel createMainModel() {
            return new MainModel();
        }
    }
}
