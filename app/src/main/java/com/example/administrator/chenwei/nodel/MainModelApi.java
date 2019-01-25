package com.example.administrator.chenwei.nodel;

import com.example.administrator.chenwei.DataBean;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018\11\29 0029.
 */

public interface MainModelApi  {
    interface onFinishListener{
        void onDataFinished(DataBean dataBean);
        void onError(String msg);
    }
    void getBobyData(FormBody body, onFinishListener listener);
}
