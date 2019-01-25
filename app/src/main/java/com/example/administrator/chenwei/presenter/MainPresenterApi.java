package com.example.administrator.chenwei.presenter;

import com.example.administrator.chenwei.base.BasePresenter;
import com.example.administrator.chenwei.view.MainView;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018\11\29 0029.
 */

public interface MainPresenterApi extends BasePresenter <MainView>{
    void getData(FormBody body);
}
