package com.zsoltdavid.weatherlocation.view.screen.main.presenter;

import com.zsoltdavid.weatherlocation.view.common.presenter.BasePresenter;
import com.zsoltdavid.weatherlocation.view.screen.main.contract.MainContract;
import com.zsoltdavid.weatherlocation.view.screen.main.model.MainModel;

/**
 * Created by Admin on 2017.04.03..
 */

public class MainPresenter extends BasePresenter<MainContract.View, MainModel>
    implements MainContract.Presenter{

    @Override
    protected MainModel buildModel() {
        return new MainModel.Builder()
                .createMainModel();
    }

    @Override
    public void attach(MainContract.View view) {
        super.attach(view);
        render();
    }
}
