package com.zsoltdavid.weatherlocation.view.screen.main.gui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.zsoltdavid.weatherlocation.R;
import com.zsoltdavid.weatherlocation.view.common.gui.BaseActivity;
import com.zsoltdavid.weatherlocation.view.screen.main.contract.MainContract;
import com.zsoltdavid.weatherlocation.view.screen.main.model.MainModel;
import com.zsoltdavid.weatherlocation.view.screen.main.presenter.MainPresenter;
import com.zsoltdavid.weatherlocation.view.screen.map.gui.MapFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainModel, MainPresenter> implements MainContract.View {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    public MainPresenter presenter() {
        return new MainPresenter();
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragmentToContainer(new MapFragment());
    }

    @Override
    public void presentView(MainModel model) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_map:
                        addFragmentToContainer(new MapFragment());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void addFragmentToContainer(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
