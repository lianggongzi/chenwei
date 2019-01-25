package com.example.administrator.chenwei.view;

import com.example.administrator.chenwei.DataBean;
import com.example.administrator.chenwei.base.BaseView;

/**
 * Created by Administrator on 2018\11\29 0029.
 */

public interface MainView extends BaseView {
    void getData(DataBean bean);
    void getError(String msg);
}
