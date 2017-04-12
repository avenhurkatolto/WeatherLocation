package com.zsoltdavid.weatherlocation.view.common.gui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsoltdavid.weatherlocation.view.common.contract.BaseView;
import com.zsoltdavid.weatherlocation.view.common.model.BaseModel;
import com.zsoltdavid.weatherlocation.view.common.presenter.BasePresenter;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Admin on 2017.04.03..
 */

public abstract class BaseFragment<M extends BaseModel, P extends BasePresenter>
        extends Fragment implements BaseView<M> {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId(), container, false);
        ButterKnife.bind(this, view);
        presenter = presenter();
        presenter.attach(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        presenter.detach();
        super.onDestroyView();
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

    public abstract
    @LayoutRes
    int layoutId();

    public abstract P presenter();

    public abstract void presentView(M model);
}
