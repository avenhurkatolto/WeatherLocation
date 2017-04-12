package com.zsoltdavid.weatherlocation.view.common.contract;

/**
 * Created by Admin on 2017.04.03..
 */

public interface BaseView<M> {
    void showLoading();

    void dismissLoading();

    void showError(String message);

    void present(M model);
}
