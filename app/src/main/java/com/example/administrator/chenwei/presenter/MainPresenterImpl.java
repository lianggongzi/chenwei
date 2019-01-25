package com.example.administrator.chenwei.presenter;

import com.example.administrator.chenwei.DataBean;
import com.example.administrator.chenwei.nodel.MainModelApi;
import com.example.administrator.chenwei.nodel.MainModelImpl;
import com.example.administrator.chenwei.view.MainView;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018\11\29 0029.
 */

public class MainPresenterImpl implements MainPresenterApi ,MainModelApi.onFinishListener{

    MainModelApi mainModelApi;
    MainView mainView;

    public MainPresenterImpl() {
        mainModelApi=new MainModelImpl();
    }

    @Override
    public void attachView(MainView view) {
        mainView=view;
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    @Override
    public void getData(FormBody body) {
        if (mainView != null) {
            mainModelApi.getBobyData(body, this);
        }
    }

    @Override
    public void onDataFinished(DataBean loginBean) {
        if (mainView != null) {
            mainView.getData(loginBean);
        }
    }

    @Override
    public void onError(String msg) {
        if (mainView != null) {
            mainView.getError(msg);
        }
    }
}
