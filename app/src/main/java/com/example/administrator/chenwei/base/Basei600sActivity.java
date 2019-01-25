package com.example.administrator.chenwei.base;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;



/**
 * Created by Administrator on 2018-07-05
 * <p>
 * 描述：基类Activity的封装
 * 在BaseActivity中进行P和V的初始化绑定
 */

public abstract class Basei600sActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mPresenter;
    private static final int REQUEST_PERMISSION = 4 << 4;
//    private ShapeLoadingDialog sweetAlertDialog;
    private Dialog dialog;
    protected int mWidth;
    protected int mHeight;
    protected float mDensity;
    protected int mDensityDpi;
    protected int mAvatarSize;
    protected float mRatio;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //注册sdk的event用于接收各种event事件
//        JMessageClient.registerEventReceiver(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        mDensityDpi = dm.densityDpi;
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
        mRatio = Math.min((float) mWidth / 1080, (float) mHeight / 1920);
        mAvatarSize = (int) (50 * mDensity);
        createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
//        App.getInstance().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销消息接收
//        JMessageClient.unRegisterEventReceiver(this);
        if (mPresenter != null)
            mPresenter.detachView();
        App.getInstance().removeActivity(this);
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    protected abstract void createPresenter();


//    @Override
//    public void showDialog(String msg) {
//        sweetAlertDialog = new ShapeLoadingDialog.Builder(this)
//                .loadText(msg)
//                .build();
//        sweetAlertDialog.show();
//
//    }

//    @Override
//    public void hideDialog() {
//        if (sweetAlertDialog != null) {
//            sweetAlertDialog.dismiss();
//        }
//
//    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showEmpty() {

    }

//    public void showToast(String msg) {
//        ToastUtil.showToast(App.getInstance().getApplicationContext(), msg);
//    }

}
