package com.zsoltdavid.weatherlocation.view.common.presenter;


import com.zsoltdavid.weatherlocation.view.common.contract.BaseView;
import com.zsoltdavid.weatherlocation.view.common.model.BaseModel;

/**
 * Created by Admin on 2017.04.03..
 */

public abstract class BasePresenter<V extends BaseView<M>, M extends BaseModel> {

    protected V view;
    protected M model;

    protected abstract M buildModel();

    protected void addToModel(M model) {
        this.model = model;
    }

    public void attach(V view) {
        this.view = view;
        this.model = buildModel();
    }

    public void detach() {
        this.view = null;
        this.model = null;
    }

    protected void render() {
        this.view.present(model);
    }
}
