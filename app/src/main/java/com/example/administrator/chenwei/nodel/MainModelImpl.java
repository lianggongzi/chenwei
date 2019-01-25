package com.example.administrator.chenwei.nodel;

import android.support.annotation.NonNull;

import com.example.administrator.chenwei.DataBean;
import com.example.administrator.chenwei.Utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;

/**
 * Created by Administrator on 2018\11\29 0029.
 */

public class MainModelImpl implements MainModelApi {
    @Override
    public void getBobyData(FormBody body, final onFinishListener listener) {
        RetrofitUtil.getApi().getData(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<DataBean>() {
                    @Override
                    public void onNext(@NonNull DataBean loginBean) {
                        listener.onDataFinished(loginBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
