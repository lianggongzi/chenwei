package com.example.administrator.chenwei.Utils;

import com.example.administrator.chenwei.api.Api;
import com.example.administrator.chenwei.url.Urls;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by HJianFei on 2017/6/29.
 * <p>
 * 描述：获取Retrofit对象
 */

public class RetrofitUtil {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private static Api api;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    public static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkhttpUtil.getOkHttpClient();
            }

            //Retrofit2后使用build设计模式
            mRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl(Urls.API_SERVER)
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();

        }
        return mRetrofit;
    }

    public static Api getApi() {

        if (api == null) {
            api = getRetrofit().create(Api.class);
        }
        return api;

    }
}
