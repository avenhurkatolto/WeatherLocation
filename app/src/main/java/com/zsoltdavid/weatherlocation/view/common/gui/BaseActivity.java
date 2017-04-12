package com.zsoltdavid.weatherlocation.view.common.gui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zsoltdavid.weatherlocation.view.common.contract.BaseView;
import com.zsoltdavid.weatherlocation.view.common.model.BaseModel;
import com.zsoltdavid.weatherlocation.view.common.presenter.BasePresenter;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Admin on 2017.04.03..
 */

public abstract class BaseActivity<M extends BaseModel, P extends BasePresenter>
        extends AppCompatActivity implements BaseView<M> {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        this.presenter = presenter();
        this.presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        this.presenter.detach();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        Timber.d("show loading");
    }

    @Override
    public void dismissLoading() {
        Timber.d("dismiss loading");
    }

    @Override
    public void showError(String message) {
        Timber.d(message);
    }

    @Override
    public void present(M model) {
        Timber.d("present view on base activity");
        presentView(model);
    }

    public abstract void presentView(M model);

    public abstract P presenter();

    public abstract @LayoutRes
    int layoutId();
}
