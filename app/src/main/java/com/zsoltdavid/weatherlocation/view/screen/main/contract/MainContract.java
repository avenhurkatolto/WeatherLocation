package com.zsoltdavid.weatherlocation.view.screen.main.contract;


import com.zsoltdavid.weatherlocation.view.common.contract.BasePresenter;
import com.zsoltdavid.weatherlocation.view.common.contract.BaseView;
import com.zsoltdavid.weatherlocation.view.screen.main.model.MainModel;

/**
 * Created by Admin on 2017.04.03..
 */

public final class MainContract {
    private MainContract() {

    }

    public interface View extends BaseView<MainModel> {

    }

    public interface Presenter extends BasePresenter {

    }
}
