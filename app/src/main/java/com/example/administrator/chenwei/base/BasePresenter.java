package com.example.administrator.chenwei.base;

/**
 * Created by Administrator on 2018-07-05.
 * <p>
 * 描述：MVP框架的简单封装 P处理层
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
